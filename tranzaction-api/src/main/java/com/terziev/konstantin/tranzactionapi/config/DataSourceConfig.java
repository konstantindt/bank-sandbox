package com.terziev.konstantin.tranzactionapi.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.derby.jdbc.ClientDataSource;

@ApplicationScoped
public class DataSourceConfig {

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {
        final ClientDataSource dataSource = new ClientDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(1527);
        dataSource.setDatabaseName("bankSandboxDB");
        return dataSource;
    }

}
