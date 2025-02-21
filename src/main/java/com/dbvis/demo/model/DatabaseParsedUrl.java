package com.dbvis.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

/**
 * Represents a parsed database URL.
 * To add more database-specific properties, create Database-specific classes and extend this.
 * Example: to support mongoDB, create a class MongoDbDatabaseParsedUrl that extends this class since mongo can have multiple hosts and ports.
 * TODO: depending on the usage convert to DTO and move to DTO package
 */
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class DatabaseParsedUrl {

    public static final String USER_PROPERTY = "user";
    public static final String PASSWORD_PROPERTY = "password";

    String url;

    DatabaseType type;

    String host;

    int port;

    String database;

    Map<String, String> properties;

    /**
     * SID used for backward compatibility with older versions
     * Can be moved to Database-specific classes if needed (e.g. OracleDatabaseParsedUrl)
     * */
    @Nullable
    String sid;

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
