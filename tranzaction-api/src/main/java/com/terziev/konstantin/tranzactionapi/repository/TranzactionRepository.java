package com.terziev.konstantin.tranzactionapi.repository;

import com.terziev.konstantin.tranzactionapi.model.Tranzaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import org.apache.meecrowave.jpa.api.Jpa;
import org.apache.meecrowave.jpa.api.Unit;

import lombok.NonNull;

@ApplicationScoped
@Jpa(transactional = false)
public class TranzactionRepository {

    @Inject
    @Unit(name = "tranzactionPersistenceInfo")
    private EntityManager entityManager;

    @Jpa
    public void save(@NonNull Tranzaction tranzaction) {
        entityManager.persist(tranzaction);
        entityManager.flush();
        entityManager.refresh(tranzaction);
    }

    public Optional<Tranzaction> find(@NonNull final String tranzactionId) {
        return Optional.ofNullable(entityManager.find(Tranzaction.class, tranzactionId));
    }

    public List<Tranzaction> findByPartyId(@NonNull final String partyId) {
        return entityManager.createNamedQuery("Tranzaction.findByPartyId", Tranzaction.class)
            .setParameter("partyId", partyId)
            .getResultList();
    }

}
