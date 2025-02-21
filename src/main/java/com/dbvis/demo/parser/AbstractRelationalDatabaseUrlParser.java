package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
abstract class AbstractRelationalDatabaseUrlParser extends AbstractDatabaseUrlParser {

    private static final Pattern URL_PATTERN = Pattern.compile("jdbc:(?<type>[^:]+)(:[^:]+)*:@?//(?<host>[^:/]+)(:(?<port>\\d+))?/(?<database>[^?]+)(\\?(?<properties>.*))?");

    @Override
    public Optional<DatabaseParsedUrl> parse(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is blank. " + getDatabaseType());
        }
        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            log.error("{}, Invalid database URL format.", getDatabaseType());
            return Optional.empty();
        }

        String type = matcher.group("type");
        if (type == null || DatabaseType.fromStringSafe(type).map(databaseType -> databaseType != getDatabaseType()).orElse(true)) {
            log.error("{}, Invalid database type.", getDatabaseType());
            return Optional.empty();
        }
        String host = matcher.group("host");
        String portStr = matcher.group("port");
        int port = portStr != null ? Integer.parseInt(portStr) : getDefaultPort();
        String database = matcher.group("database");
        String propertiesStr = matcher.group("properties");

        Map<String, String> properties = buildProperties(propertiesStr);

        return Optional.of(DatabaseParsedUrl.builder()
                .url(url)
                .type(getDatabaseType())
                .host(host)
                .port(port)
                .database(database)
                .properties(properties)
                .build());
    }

}
