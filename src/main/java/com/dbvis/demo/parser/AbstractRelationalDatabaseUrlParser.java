package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class AbstractRelationalDatabaseUrlParser extends AbstractDatabaseUrlParser {

    private static final Pattern URL_PATTERN = Pattern.compile("jdbc:(?<type>[^:]+)(:[^:]+)*:@?//(?<host>[^:/]+)(:(?<port>\\d+))?/(?<database>[^?]+)(\\?(?<properties>.*))?");

    @Override
    public DatabaseParsedUrl parse(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is blank. " + getDatabaseType());
        }
        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid database URL format. " + getDatabaseType());
        }

        String type = matcher.group("type");
        if (type == null || DatabaseType.fromString(type) != getDatabaseType()) {
            throw new IllegalArgumentException("Invalid database type. " + getDatabaseType());
        }
        String host = matcher.group("host");
        String portStr = matcher.group("port");
        int port = portStr != null ? Integer.parseInt(portStr) : getDefaultPort();
        String database = matcher.group("database");
        String propertiesStr = matcher.group("properties");

        Map<String, String> properties = buildProperties(propertiesStr);

        return new DatabaseParsedUrl(url, getDatabaseType(), host, port, database, properties);
    }

}
