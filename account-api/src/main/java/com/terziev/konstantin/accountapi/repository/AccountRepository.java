package com.terziev.konstantin.accountapi.repository;

import com.terziev.konstantin.accountapi.model.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.Optional;

import lombok.NonNull;

import org.apache.meecrowave.jpa.api.Jpa;
import org.apache.meecrowave.jpa.api.Unit;

@ApplicationScoped
@Jpa(transactional = false)
public class AccountRepository {

    @Inject
    @Unit(name = "accountPersistenceInfo")
    private EntityManager entityManager;

    @Jpa
    public void save(@NonNull Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
    }

    public Optional<Account> find(@NonNull final String accountId) {
        return Optional.ofNullable(entityManager.find(Account.class, accountId));
    }

}
