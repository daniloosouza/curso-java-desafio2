package com.desafio2.apis.dto;

import com.desafio2.apis.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjValuesDTO {

    private Integer accountNumber;

    private Integer accountAgency;

    private Double value;

}
