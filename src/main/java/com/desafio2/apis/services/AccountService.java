package com.desafio2.apis.services;

import com.desafio2.apis.commons.AccountOperations;
import com.desafio2.apis.commons.interfaces.WireTimeRule;
import com.desafio2.apis.domain.Account;
import com.desafio2.apis.domain.History;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.dto.BankWireDTO;
import com.desafio2.apis.dto.ObjValuesDTO;
import com.desafio2.apis.repository.AccountRepository;
import com.desafio2.apis.repository.HistoryRepository;
import com.desafio2.apis.services.exceptions.AccountManagementException;
import com.desafio2.apis.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    @Autowired
    private HistoryRepository histRepo;

    @Autowired
    private AccountOperations ops;

    @Autowired
    private WireTimeRule wireTimeRule;

    public List<Account> findAll() {
        return repo.findAll();
    }

    public List<History> findAllHistory() {
        return histRepo.findAll();
    }

    public Account insert(Account obj) {
        ops.verifyAccount(obj.getAccountNumber(), obj.getAccountAgency(), false);
        Account ac = repo.insert(obj);

        History history = ops.buildHistoryData(obj, "Cadastro", obj.getAccountBalance());
        histRepo.save(history);
        System.out.printf("Conta criada:: %s", ac.toString());
        return ac;
    }

    public Account fromDTO(AccountDTO objDto) {
        return new Account(objDto.getId(), objDto.getAccountNumber(), objDto.getAccountAgency(), objDto.getCustomerName(), objDto.getAccountBalance(),
                           objDto.getAccountLimit(), objDto.getAccountType());
    }

    public Account findById(String id) {

        Account account = repo.findById(id).orElse(null);
        if (account == null) {
          throw new ObjectNotFoundException("Objeto não encontrado");
        }
        System.out.printf("Conta encontrada:: %s", account.toString());
        return account;
    }

    public void bankDeposit(ObjValuesDTO objDTO) {
        ops.valueIsValid(objDTO.getValue());

        Account account = ops.verifyAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency(), true);
        account.setAccountBalance(account.getAccountBalance() + objDTO.getValue());
        repo.save(account);
        System.out.printf("Depósito efetuado:: %s", account.toString());

        History history = ops.buildHistoryData(account, "Depósito", objDTO.getValue());
        histRepo.save(history);
    }

    public void bankDraw(ObjValuesDTO objDTO) {
        Double drawValue = objDTO.getValue();
        ops.valueIsValid(drawValue);

        Account account = ops.verifyAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency(), true);

        boolean validatedWire = wireTimeRule.validatedWire(drawValue);
        if (drawValue > account.getAccountBalance()) {
            throw new AccountManagementException("A conta informada não possui saldo suficiente!");
        } else if (drawValue > account.getAccountLimit()) {
            throw new AccountManagementException("O valor informado é maior que o limite de transação disponível!");
        } else if (!validatedWire) {
            throw new AccountManagementException("Limite de transações entre " + wireTimeRule.getInitLimit().toString() + "h e " +
                                                         wireTimeRule.getEndLimit().toString() + "h excedido!");
        } else {
            account.setAccountBalance(account.getAccountBalance() - drawValue);
            account.setAccountLimit(account.getAccountLimit() - drawValue);
            repo.save(account);
            System.out.printf("Saque efetuado:: %s", account.toString());

            History history = ops.buildHistoryData(account, "Saque", drawValue);
            histRepo.save(history);
        }
    }

    public void changeLimit(ObjValuesDTO objDTO)  {
        Double value = objDTO.getValue();
        ops.valueIsValid(value);
        Account account = ops.verifyAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency(), true);

        account.setAccountLimit(value);
        repo.save(account);
        System.out.printf("Limite alterado:: %s", account.toString());

        History history = ops.buildHistoryData(account, "Alteração de Limite", value);
        histRepo.save(history);
    }

    public void bankWire(BankWireDTO bwDTO) {
        Double wireValue = bwDTO.getValue();
        boolean validatedWire = wireTimeRule.validatedWire(wireValue);
        ops.valueIsValid(wireValue);

        Account accountOrigin = ops.verifyAccount(bwDTO.getAccountOrigin().getAccountNumber(), bwDTO.getAccountOrigin().getAccountAgency(), true);
        Account accountDestiny = ops.verifyAccount(bwDTO.getAccountDestiny().getAccountNumber(), bwDTO.getAccountDestiny().getAccountAgency(), true);

        if (accountOrigin.getAccountBalance() < wireValue) {
            throw new AccountManagementException("Saldo insuficiente para realizar a transferência bancária!");
        } else if (accountOrigin.getAccountLimit() < wireValue) {
            throw new AccountManagementException("Limite de transações diárias excedido!");
        } else if(!validatedWire){
            throw new AccountManagementException("Limite de transações entre " + wireTimeRule.getInitLimit().toString() + "h e " +
                                                         wireTimeRule.getEndLimit().toString() + "h excedido!");
        } else {
            accountOrigin.setAccountBalance(accountOrigin.getAccountBalance() - wireValue);
            accountOrigin.setAccountLimit(accountOrigin.getAccountLimit() - wireValue);
            accountDestiny.setAccountBalance(accountDestiny.getAccountBalance() + wireValue);
            repo.save(accountOrigin);
            System.out.printf("Transferência Origem:: %s", accountOrigin.toString());
            repo.save(accountDestiny);
            System.out.printf("Transferência Destino:: %s", accountDestiny.toString());
            History historyOrigin = ops.buildHistoryData(accountOrigin, "Transferência - Origem", wireValue);
            History historyDestino = ops.buildHistoryData(accountOrigin, "Transferência - Destino", wireValue);
            histRepo.save(historyOrigin);
            histRepo.save(historyDestino);
        }

    }
}
