package com.terziev.konstantin.tranzactionapi.service;

import com.terziev.konstantin.tranzactionapi.model.Tranzaction;
import com.terziev.konstantin.tranzactionapi.repository.TranzactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import lombok.NonNull;

@ApplicationScoped
public class TranzactionService {

    @Inject
    private TranzactionRepository tranzactionRepository;

    public Tranzaction addTranzaction(@NonNull final Tranzaction tranzaction) {
        Tranzaction newTranzaction = new Tranzaction();

        newTranzaction.setId(UUID.randomUUID().toString());
        newTranzaction.setSource(tranzaction.getSource());
        newTranzaction.setTarget(tranzaction.getTarget());
        newTranzaction.setValue(tranzaction.getValue());

        tranzactionRepository.save(newTranzaction);

        return newTranzaction;
    }

    public Optional<Tranzaction> findTranzaction(@NonNull final String tranzactionId) {
        return tranzactionRepository.find(tranzactionId);
    }

    public List<Tranzaction> findTranzactionsByPartyId(@NonNull final String partyId) {
        return tranzactionRepository.findByPartyId(partyId);
    }

}
