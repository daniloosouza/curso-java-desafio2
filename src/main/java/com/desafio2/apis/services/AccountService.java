package com.desafio2.apis.services;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.dto.AccountDTO;
import com.desafio2.apis.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public Account insert(Account obj) {
        return repo.insert(obj);
    }

    public Account fromDTO(AccountDTO objDto) {
        return new Account(objDto.getId(), objDto.getAccountNumber(), objDto.getAccountAgency(), objDto.getCustomerName(), objDto.getAccountBalance(),
                           objDto.getAccountLimit(), objDto.getAccountType());
    }

}
