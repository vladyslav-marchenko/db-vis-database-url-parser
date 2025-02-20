package com.dbvis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class DatabaseParsedUrl {

    public static final String USER_PROPERTY = "user";
    public static final String PASSWORD_PROPERTY = "password";

    final String url;
    final DatabaseType type;
    final String host;
    final int port;
    final String database;
    final Map<String, String> properties;

    public Optional<String> findUser() {
        return Optional.ofNullable(getProperties().get(USER_PROPERTY));
    }

    public Optional<String> findPassword() {
        return Optional.ofNullable(getProperties().get(PASSWORD_PROPERTY));
    }

    public Optional<String> findProperty(String key) {
        return Optional.ofNullable(getProperties().get(key));
    }

    public Map<String, String> getProperties() {
        if (properties == null) {
            return new HashMap<>();
        }
        return properties;
    }

}
