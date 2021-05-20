package com.terziev.konstantin.statementapi.model;

import com.terziev.konstantin.statementapi.model.Account;
import com.terziev.konstantin.statementapi.model.Tranzaction;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Statement {

    private Account account;

    private List<Tranzaction> tranzactions;

}
