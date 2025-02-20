package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

// TODO: support old SID format
@Component
class OracleDatabaseUrlParser extends AbstractRelationalDatabaseUrlParser {

    @Override
    protected int getDefaultPort() {
        return 1521;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.ORACLE;
    }

}
