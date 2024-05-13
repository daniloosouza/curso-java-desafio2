package com.desafio2.apis.commons;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.domain.History;
import com.desafio2.apis.repository.AccountRepository;
import com.desafio2.apis.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccountOperations {

    @Autowired
    private AccountRepository repo;

    public void valueIsValid(Double value) throws ObjectNotFoundException {
        if (value < 0) {
//            throw new AccountManagementException("O valor informado deve ser maior que 0!");
            throw new ObjectNotFoundException("O valor informado deve ser maior que 0!");
        }
    }

    public Account verifyAccount(int number, int agency, boolean verifyIfExists)
            throws ObjectNotFoundException {

        Account account = repo.findAccount(number, agency);

        if (account.getAccountAgency().equals(agency) && account.getAccountNumber().equals(number)) {
            if (verifyIfExists) {
                return account;
            } else {
//                throw new AccountManagementException("A conta informada já existe!");
                throw new ObjectNotFoundException("A conta informada já existe!");
            }
        }

        if (verifyIfExists) {
//            throw new AccountManagementException("A conta informada não existe!");
            throw new ObjectNotFoundException("A conta informada não existe!");
        } else {
            return account;
        }
    }

    public History buildHistoryData(Account account, String action, Double value){
        return new History(null, LocalDateTime.now(), action, account.getAccountNumber(), account.getAccountAgency(), account.getCustomerName(),
                           value, account.getAccountBalance(), account.getAccountType());
    }

}
