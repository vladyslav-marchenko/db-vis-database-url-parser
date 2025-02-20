package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

@Component
class MySqlDatabaseUrlParser extends AbstractRelationalDatabaseUrlParser {

    @Override
    protected int getDefaultPort() {
        return 3306;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MYSQL;
    }

}
