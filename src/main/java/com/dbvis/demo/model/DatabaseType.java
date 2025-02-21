package com.dbvis.demo.model;

import java.util.Optional;

public enum DatabaseType {

    MYSQL,
    POSTGRESQL,
    ORACLE,
    MONGODB;

    public static Optional<DatabaseType> fromStringSafe(String type) {
        switch (type.toLowerCase()) {
            case "mysql":
                return Optional.of(MYSQL);
            case "postgresql":
                return Optional.of(POSTGRESQL);
            case "oracle":
                return Optional.of(ORACLE);
            case "mongodb":
                return Optional.of(MONGODB);
            default:
                return Optional.empty();
        }
    }

}
