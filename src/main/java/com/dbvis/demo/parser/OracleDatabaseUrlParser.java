package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class OracleDatabaseUrlParser extends AbstractRelationalDatabaseUrlParser {

    private static final Pattern SID_PATTERN = Pattern.compile("jdbc:oracle:thin:@(?<host>[^:]+):(?<port>\\d+):(?<sid>[^?]+)");

    @Override
    protected int getDefaultPort() {
        return 1521;
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.ORACLE;
    }

    @Override
    public Optional<DatabaseParsedUrl> parse(String url) {
        Optional<DatabaseParsedUrl> databaseParsedUrlOpt = super.parse(url);
        if (databaseParsedUrlOpt.isPresent()) {
            return databaseParsedUrlOpt;
        } else {
            return parseOracleSpecific(url);
        }
    }

    /**
     * Supports older SID format: jdbc:oracle:thin:@host:port:sid
     * */
    private Optional<DatabaseParsedUrl> parseOracleSpecific(String url) {
        Matcher matcher = SID_PATTERN.matcher(url);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        String host = matcher.group("host");
        int port = Integer.parseInt(matcher.group("port"));
        String sid = matcher.group("sid");

        return Optional.of(DatabaseParsedUrl.builder()
                .url(url)
                .type(getDatabaseType())
                .host(host)
                .port(port)
                .sid(sid)
                .build());
    }

}
