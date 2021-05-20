package com.terziev.konstantin.tranzactionapi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(
    name="Tranzaction.findByPartyId",
    query="SELECT tranzaction FROM Tranzaction tranzaction WHERE tranzaction.source = :partyId OR tranzaction.target = :partyId"
)
@Getter
@Setter
@NoArgsConstructor
public class Tranzaction {

    @Id
    private String id;

    private String source;

    private String target;

    private BigDecimal value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    private Timestamp createdDate;

}
