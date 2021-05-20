package com.terziev.konstantin.statementapi.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private String id;

    private String userId;

    private BigDecimal initialBalance;

    private BigDecimal currentBalance;

    private Date createdDate;

}
