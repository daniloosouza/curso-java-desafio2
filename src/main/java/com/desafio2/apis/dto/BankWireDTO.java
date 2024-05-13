package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankWireDTO {

    private AccountOrigin accountOrigin;

    private AccountDestiny accountDestiny;

    private Double value;

}
