package com.desafio2.apis.domain;

import com.desafio2.apis.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accounts")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String id;

    @JsonProperty(required = true)
    private Integer accountNumber;

    @JsonProperty(required = true)
    private Integer accountAgency;

    @JsonProperty(required = true)
    private String customerName;

    @JsonProperty(required = true)
    private Double accountBalance;

    @JsonProperty(required = true)
    private Double accountLimit;

    @JsonProperty(required = true)
    private AccountType accountType;

}
