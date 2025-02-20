package com.dbvis.demo.parserstrategy;

import com.dbvis.demo.model.DatabaseType;
import com.dbvis.demo.parser.DatabaseUrlParser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DatabaseUrlParserStrategy {

    private final Map<DatabaseType, DatabaseUrlParser> parsersMap = new EnumMap<>(DatabaseType.class);

    public DatabaseUrlParserStrategy(Collection<DatabaseUrlParser> parsers) {
        parsers.forEach(parser -> parsersMap.put(parser.getDatabaseType(), parser));
    }

    public DatabaseUrlParser getParser(DatabaseType databaseType) {
        return Optional.ofNullable(parsersMap.get(databaseType))
                .orElseThrow(() -> new IllegalArgumentException("No parser found for database type: " + databaseType));
    }

}
