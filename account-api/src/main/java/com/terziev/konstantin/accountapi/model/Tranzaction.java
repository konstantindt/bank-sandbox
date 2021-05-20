package com.terziev.konstantin.accountapi.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tranzaction {

    private String id;

    private String source;

    private String target;

    private BigDecimal value;

    private Date createdDate;

}
