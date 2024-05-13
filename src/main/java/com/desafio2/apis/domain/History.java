package com.desafio2.apis.domain;

import com.desafio2.apis.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="history")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    private String id;
    private LocalDateTime date;
    private String action;
    private Integer accountNumber;
    private Integer accountAgency;
    private String customerName;
    private Double value;
    private Double balance;
    private AccountType type;
}
