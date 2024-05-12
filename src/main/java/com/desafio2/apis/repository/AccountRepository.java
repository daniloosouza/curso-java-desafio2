package com.desafio2.apis.repository;

import com.desafio2.apis.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query("{ 'accountNumber': ?0, 'accountAgency': ?1 }")
    Account findAccount(Integer accountNumber, Integer accountAgency);
}
