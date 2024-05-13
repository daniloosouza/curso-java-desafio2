package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import com.desafio2.apis.domain.History;
import com.desafio2.apis.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {
    private String id;
    private String date;
    private String action;
    private Integer accountNumber;
    private Integer accountAgency;
    private String customerName;
    private Double value;
    private Double balance;
    private AccountType type;

    public HistoryDTO(History obj) {

        date = obj.getDate().toString();
        id = obj.getId();
        action = obj.getAction();
        accountNumber = obj.getAccountNumber();
        accountAgency = obj.getAccountAgency();
        customerName = obj.getCustomerName();
        value = obj.getValue();
        balance = obj.getBalance();
        type = obj.getType();
    }
}
