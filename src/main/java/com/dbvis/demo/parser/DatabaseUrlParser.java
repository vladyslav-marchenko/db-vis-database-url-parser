package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;

import java.util.Optional;

public interface DatabaseUrlParser {

    /**
     * Parse the given URL and return a {@link DatabaseParsedUrl} object if the URL is valid.
     * @param url the URL to parse
     * @return an {@link Optional} containing the parsed URL if the URL is valid, or an empty {@link Optional} otherwise
     * @throws IllegalArgumentException if the URL is blank
     */
    Optional<DatabaseParsedUrl> parse(String url);

    DatabaseType getDatabaseType();

}
