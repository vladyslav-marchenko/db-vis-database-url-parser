package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class MongoDbDatabaseUrlParser extends AbstractNonRelationalDatabaseUrlParser {

    @Override
    protected int getDefaultPort() {
        return 27017;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MONGODB;
    }

    @Override
    public Optional<DatabaseParsedUrl> parse(String url) {
        throw new UnsupportedOperationException("Not implemented yet. " + getDatabaseType());
    }

}
