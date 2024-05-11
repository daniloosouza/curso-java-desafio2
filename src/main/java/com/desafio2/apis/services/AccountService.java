package com.desafio2.apis.services;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.repository.AccountRepository;
import com.desafio2.apis.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

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
}
