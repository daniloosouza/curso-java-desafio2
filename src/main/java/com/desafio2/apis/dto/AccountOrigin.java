package com.desafio2.apis.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountOrigin {

    @NotNull(message = "accountNumber may not be null")
    private Integer accountNumber;

    @NotNull(message = "accountAgency may not be null")
    private Integer accountAgency;
}
