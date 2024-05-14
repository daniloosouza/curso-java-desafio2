package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.domain.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id;

    @NotBlank(message = "accountNumber may not be empty")
    @NotNull(message = "accountNumber may not be null")
    private Integer accountNumber;

    @NotBlank(message = "accountAgency may not be empty")
    @NotNull(message = "accountAgency may not be null")
    private Integer accountAgency;

    @NotBlank(message = "customerName may not be empty")
    @NotNull(message = "customerName may not be null")
    private String customerName;

    @NotBlank(message = "accountBalance may not be empty")
    @NotNull(message = "accountBalance may not be null")
    private Double accountBalance;

    @NotBlank(message = "accountLimit may not be empty")
    @NotNull(message = "accountLimit may not be null")
    private Double accountLimit;

    @NotBlank(message = "accountType may not be empty")
    @NotNull(message = "accountType may not be null")
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
