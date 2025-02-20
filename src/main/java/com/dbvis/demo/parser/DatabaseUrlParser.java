package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;

public interface DatabaseUrlParser {

    DatabaseParsedUrl parse(String url);

    DatabaseType getDatabaseType();

}
