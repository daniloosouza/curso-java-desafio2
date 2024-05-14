package com.desafio2.apis.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjValuesDTO {

    @NotEmpty(message = "accountNumber may not be empty")
    @NotNull(message = "accountNumber may not be null")
    private Integer accountNumber;

    @NotEmpty(message = "accountAgency may not be empty")
    @NotNull(message = "accountAgency may not be null")
    private Integer accountAgency;

    @NotEmpty(message = "value may not be empty")
    @NotNull(message = "value may not be null")
    private Double value;

}
