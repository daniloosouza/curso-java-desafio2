package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankWireDTO {

    @NotNull(message = "accountOrigin may not be null")
    private AccountOrigin accountOrigin;

    @NotNull(message = "accountDestiny may not be null")
    private AccountDestiny accountDestiny;

    @NotNull(message = "value may not be null")
    private Double value;

}
