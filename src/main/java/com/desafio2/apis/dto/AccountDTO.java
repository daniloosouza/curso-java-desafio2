package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id;

    private Integer accountNumber;

    private Integer accountAgency;

    private String customerName;

    private Double accountBalance;

    private Double accountLimit;

    private AccountType accountType;

    public AccountDTO(Account obj) {

        id = obj.getId();
        accountNumber = obj.getAccountNumber();
        accountAgency = obj.getAccountAgency();
        customerName = obj.getCustomerName();
        accountBalance = obj.getAccountBalance();
        accountLimit = obj.getAccountLimit();
        accountType = obj.getAccountType();
    }
}
