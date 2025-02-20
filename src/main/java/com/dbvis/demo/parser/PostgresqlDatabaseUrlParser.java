package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

@Component
class PostgresqlDatabaseUrlParser extends AbstractRelationalDatabaseUrlParser {

    @Override
    protected int getDefaultPort() {
        return 5432;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.POSTGRESQL;
    }

}
