package com.terziev.konstantin.accountapi.service;

import com.terziev.konstantin.accountapi.model.Account;
import com.terziev.konstantin.accountapi.model.Tranzaction;
import com.terziev.konstantin.accountapi.repository.AccountRepository;

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
public class AccountService {

    @Inject
    private AccountRepository accountRepository;

    public Account addAccount(@NonNull final Account account) {
        Account newAccount = new Account();

        newAccount.setId(UUID.randomUUID().toString());
        newAccount.setUserId(account.getUserId());
        newAccount.setInitialBalance(account.getInitialBalance());

        accountRepository.save(newAccount);

        return newAccount;
    }

    public Optional<Account> findAccount(@NonNull final String accountId) {
        Optional<Account> optionalAccount = accountRepository.find(accountId);

        optionalAccount.ifPresent(this::populateCurrentBalance);

        return optionalAccount;
    }

    public void populateCurrentBalance(@NonNull final Account account) {
        List<Tranzaction> accountTranzactions = Collections.emptyList();
        final Client client = ClientBuilder.newClient();

        try {
            accountTranzactions = client.target("http://localhost:8070")
                .path("/api/tranzactions")
                .queryParam("partyId", account.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Tranzaction>>(){});
        } finally {
            client.close();
        }

        final BigDecimal valueIn = this.sumTranzactionValuesForAccountParty(
            accountTranzactions,
            Tranzaction::getTarget,
            account.getId()
        );
        final BigDecimal valueOut = this.sumTranzactionValuesForAccountParty(
            accountTranzactions,
            Tranzaction::getSource,
            account.getId()
        );

        account.setCurrentBalance(account.getInitialBalance().add(valueIn).subtract(valueOut));
    }

    public BigDecimal sumTranzactionValuesForAccountParty(@NonNull final List<Tranzaction> accountTranzactions,
                                                          @NonNull final Function<Tranzaction, String> tranzactionPartyMapper,
                                                          @NonNull final String accountId) {
        return accountTranzactions.stream()
            .filter(tranzaction -> accountId.equals(tranzactionPartyMapper.apply(tranzaction)))
            .map(Tranzaction::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
