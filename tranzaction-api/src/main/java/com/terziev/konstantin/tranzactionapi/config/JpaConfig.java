package com.terziev.konstantin.tranzactionapi.config;

import com.terziev.konstantin.tranzactionapi.model.Tranzaction;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;

@ApplicationScoped
public class JpaConfig {

    @Produces
    public PersistenceUnitInfoBuilder unit(final DataSource dataSource) {
        return new PersistenceUnitInfoBuilder()
                .setUnitName("tranzactionPersistenceInfo")
                .setDataSource(dataSource)
                .setExcludeUnlistedClasses(true)
                .addManagedClazz(Tranzaction.class)
                .addProperty("openjpa.RuntimeUnenhancedClasses", "unsupported")
                .addProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema")
                .addProperty("openjpa.Log", "DefaultLevel=INFO, Runtime=TRACE, Tool=TRACE, SQL=TRACE");
    }

}
