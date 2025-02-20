package com.dbvis.demo.model;

public enum DatabaseType {

    MYSQL,
    POSTGRESQL,
    ORACLE,
    MONGODB;

    public static DatabaseType fromString(String type) {
        switch (type.toLowerCase()) {
            case "mysql":
                return MYSQL;
            case "postgresql":
                return POSTGRESQL;
            case "oracle":
                return ORACLE;
            default:
                throw new IllegalArgumentException("Unsupported database type: " + type);
        }
    }

}
