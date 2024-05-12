package com.desafio2.apis.services;

import com.desafio2.apis.commons.AccountOperations;
import com.desafio2.apis.commons.interfaces.WireTimeRule;
import com.desafio2.apis.domain.Account;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.dto.ObjValuesDTO;
import com.desafio2.apis.repository.AccountRepository;
import com.desafio2.apis.services.exceptions.ObjectNotFoundException;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    @Autowired
    private AccountOperations ops;

    @Autowired
    private WireTimeRule wireTimeRule;

    public List<Account> findAll() {
        return repo.findAll();
    }

    public Account insert(Account obj) {
        return repo.insert(obj);
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
        return account;
    }

    public void bankDeposit(ObjValuesDTO objDTO) {
        ops.valueIsValid(objDTO.getValue());

        Account account = repo.findAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency());
        if (account == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        account.setAccountBalance(account.getAccountBalance() + objDTO.getValue());
        repo.save(account);
    }

    public void bankDraw(ObjValuesDTO objDTO) {
        Double drawValue = objDTO.getValue();
        ops.valueIsValid(drawValue);

        Account account = repo.findAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency());
        if (account == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }

        boolean validatedWire = wireTimeRule.validatedWire(drawValue);
        if (drawValue > account.getAccountBalance()) {
            //throw new AccountManagementException("A conta informada não possui saldo suficiente!");
            throw new ObjectNotFoundException("A conta informada não possui saldo suficiente!");
        } else if (drawValue > account.getAccountLimit()) {
            //throw new AccountManagementException("O valor informado é maior que o limite de transação disponível!");
            throw new ObjectNotFoundException("O valor informado é maior que o limite de transação disponível!");
        } else if (!validatedWire) {
//            throw new AccountManagementException("Limite de transações entre " + wireTimeRule.getInitLimit().toString() + "h e " +
//                                                         wireTimeRule.getEndLimit().toString() + "h excedido!");
            throw new ObjectNotFoundException("Limite de transações entre " + wireTimeRule.getInitLimit().toString() + "h e " +
                                                         wireTimeRule.getEndLimit().toString() + "h excedido!");
        } else {
            account.setAccountBalance(account.getAccountBalance() - drawValue);
            account.setAccountLimit(account.getAccountLimit() - drawValue);
            repo.save(account);
            //buildHistoryData.addHistoryData(obj, "Saque", drawValue);
        }
    }

    public void changeLimit(ObjValuesDTO objDTO) {
        ops.valueIsValid(objDTO.getValue());
        Account account = ops.verifyAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency(), true);

//        Account account = repo.findAccount(objDTO.getAccountNumber(), objDTO.getAccountAgency());
//        if (account == null) {
//            throw new ObjectNotFoundException("Objeto não encontrado");
//        }
        account.setAccountLimit(objDTO.getValue());
        repo.save(account);
    }
}
