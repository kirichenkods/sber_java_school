package ru.sber.db;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DataConnection {
    @Value("${postgresql.url}")
    private String url;
    @Value("${postgresql.username}")
    private String username;
    @Value("${postgresql.password}")
    private String password;
}
