package com.terziev.konstantin.statementapi.service;

import com.terziev.konstantin.statementapi.model.Account;
import com.terziev.konstantin.statementapi.model.Statement;
import com.terziev.konstantin.statementapi.model.Tranzaction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import lombok.NonNull;

@ApplicationScoped
public class StatementService {

    public Statement getStatement(@NonNull final String accountId) {
        Statement statement = new Statement();

        statement.setAccount(this.findAccount(accountId));
        statement.setTranzactions(this.findTranzactions(accountId));

        return statement;
    }

    public Account findAccount(@NonNull final String accountId) {
        final Client client = ClientBuilder.newClient();

        try {
            return client.target("http://localhost:8080")
                .path(String.format("/api/accounts/%s", accountId))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Account.class);
        } finally {
            client.close();
        }
    }

    public List<Tranzaction> findTranzactions(@NonNull final String accountId) {
        List<Tranzaction> accountTranzactions = Collections.emptyList();
        final Client client = ClientBuilder.newClient();

        try {
            accountTranzactions = client.target("http://localhost:8070")
                .path("/api/tranzactions")
                .queryParam("partyId", accountId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Tranzaction>>(){});
        } finally {
            client.close();
        }

        return accountTranzactions;
    }

}
