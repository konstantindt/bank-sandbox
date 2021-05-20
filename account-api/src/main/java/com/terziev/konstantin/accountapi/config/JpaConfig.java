package com.terziev.konstantin.accountapi.config;

import com.terziev.konstantin.accountapi.model.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;

@ApplicationScoped
public class JpaConfig {

    @Produces
    public PersistenceUnitInfoBuilder unit(final DataSource dataSource) {
        return new PersistenceUnitInfoBuilder()
            .setUnitName("accountPersistenceInfo")
            .setDataSource(dataSource)
            .setExcludeUnlistedClasses(true)
            .addManagedClazz(Account.class)
            .addProperty("openjpa.RuntimeUnenhancedClasses", "unsupported")
            .addProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema")
            .addProperty("openjpa.Log", "DefaultLevel=INFO, Runtime=TRACE, Tool=TRACE, SQL=TRACE");
    }

}
