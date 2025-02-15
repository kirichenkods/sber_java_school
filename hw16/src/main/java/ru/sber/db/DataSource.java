package ru.sber.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSource extends DriverManagerDataSource {
    public DataSource(DataConnection dataConnection) {
        this.setUrl(dataConnection.getUrl());
        this.setUsername(dataConnection.getUsername());
        this.setPassword(dataConnection.getPassword());
    }
}
