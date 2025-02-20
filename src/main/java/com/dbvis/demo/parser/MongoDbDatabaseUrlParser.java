package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class MongoDbDatabaseUrlParser extends AbstractNonRelationalDatabaseUrlParser {

    private static final Pattern URL_PATTERN = Pattern.compile(
            "mongodb(\\+srv)?:\\/\\/((?<user>[^:]+):(?<password>[^@]+)@)?(?<hosts>[^/]+)(/(?<database>[^?]+))?(\\?(?<properties>.*))?");

    @Override
    protected int getDefaultPort() {
        return 27017;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MONGODB;
    }

    @Override
    public DatabaseParsedUrl parse(String url) {
        throw new UnsupportedOperationException("Not implemented yet. " + getDatabaseType());
        /*if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL is blank. " + getDatabaseType());
        }
        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid URL format. " + getDatabaseType());
        }

        String username = matcher.group("user");
        String password = matcher.group("password");
        String hosts = matcher.group("hosts");
        String database = matcher.group("database");
        String optionsStr = matcher.group("properties");

        Map<String, String> properties = buildProperties(optionsStr);

        DatabaseParsedUrl databaseParsedUrl = new DatabaseParsedUrl(url, getDatabaseType(), hosts, getDefaultPort(), database, properties);
        databaseParsedUrl.getProperties().put(DatabaseParsedUrl.USER_PROPERTY, username);
        databaseParsedUrl.getProperties().put(DatabaseParsedUrl.PASSWORD_PROPERTY, password);

        return databaseParsedUrl;*/
    }

}
