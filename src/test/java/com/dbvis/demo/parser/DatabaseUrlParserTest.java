package com.dbvis.demo.parser;

import com.dbvis.demo.model.DatabaseParsedUrl;
import com.dbvis.demo.model.DatabaseType;
import com.dbvis.demo.parserstrategy.DatabaseUrlParserStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DatabaseUrlParserTest {

    @Autowired
    private DatabaseUrlParserStrategy databaseUrlParserStrategy;

    @Test
    void testParseBlankUrl() {
        // act: test null
        assertThrows(IllegalArgumentException.class, () ->
                databaseUrlParserStrategy.getParser(DatabaseType.MYSQL).parse(null));

        // act: test empty
        assertThrows(IllegalArgumentException.class, () ->
                databaseUrlParserStrategy.getParser(DatabaseType.MYSQL).parse(""));
    }

    @Test
    void testParseUrlWithInvalidPrefix() {
        // arr
        String url = "jdbc:invalid://localhost:3306/dbvis";

        // act
        var databaseParsedUrlOpt = databaseUrlParserStrategy.getParser(DatabaseType.MYSQL).parse(url);

        // assert
        assertTrue(databaseParsedUrlOpt.isEmpty());
    }

    @Test
    void testParseMySqlUrlWithPort() {
        // arrange
        String url = "jdbc:mysql://localhost:3306/dbvis";

        // act
        DatabaseParsedUrl actual = databaseUrlParserStrategy.getParser(DatabaseType.MYSQL).parse(url).orElseThrow();

        // assert
        assertEquals("localhost", actual.getHost());
        assertEquals(3306, actual.getPort());
        assertEquals("dbvis", actual.getDatabase());
    }

    @Test
    void testParseMySqlUrlWithoutPort() {
        // arrange
        String url = "jdbc:mysql://localhost/dbvis";

        // act
        DatabaseParsedUrl actual = databaseUrlParserStrategy.getParser(DatabaseType.MYSQL).parse(url).orElseThrow();

        // assert
        assertEquals("localhost", actual.getHost());
        assertEquals(3306, actual.getPort());
        assertEquals("dbvis", actual.getDatabase());
        assertTrue(actual.findUser().isEmpty());
        assertTrue(actual.findPassword().isEmpty());
    }

    @Test
    void testParsePostgresUrlWithProperties() {
        // arrange
        String url = "jdbc:postgresql://db.other@localhost/otherdb?connect_timeout=10&application_name=myapp&user=postgres&password=postgres";

        // act
        DatabaseParsedUrl actual = databaseUrlParserStrategy.getParser(DatabaseType.POSTGRESQL).parse(url).orElseThrow();

        // assert
        assertEquals("db.other@localhost", actual.getHost());
        assertEquals(5432, actual.getPort());
        assertEquals("otherdb", actual.getDatabase());
        assertEquals("postgres", actual.findUser().orElseThrow());
        assertEquals("postgres", actual.findPassword().orElseThrow());
    }

    @Test
    void testParseOracleUrl() {
        // arrange
        String url = "jdbc:oracle:thin:@//db.example.com:1521/orclpdb?user=myuser&password=mypassword&defaultRowPrefetch=100&oracle.net.CONNECT_TIMEOUT=5000";

        // act
        DatabaseParsedUrl actual = databaseUrlParserStrategy.getParser(DatabaseType.ORACLE).parse(url).orElseThrow();

        // assert
        assertEquals("db.example.com", actual.getHost());
        assertEquals(1521, actual.getPort());
        assertEquals("orclpdb", actual.getDatabase());
        assertEquals("myuser", actual.findUser().orElseThrow());
        assertEquals("mypassword", actual.findPassword().orElseThrow());
        actual.findProperty("oracle.net.CONNECT_TIMEOUT").ifPresent(value -> assertEquals("5000", value));
    }

    @Test
    void testParseOracleUrlSidFormat() {
        // arrange
        String url = "jdbc:oracle:thin:@db.server.example.com:1521:prod";

        // act
        DatabaseParsedUrl actual = databaseUrlParserStrategy.getParser(DatabaseType.ORACLE).parse(url).orElseThrow();

        // assert
        assertEquals("db.server.example.com", actual.getHost());
        assertEquals(1521, actual.getPort());
        assertEquals("prod", actual.getSid());
    }

    @Test
    void testParseMongoUrl() {
        // arrange
        String url = "mongodb+srv://user:password@cluster.example.mongodb.net/db";

        // act
        assertThrows(UnsupportedOperationException.class, () ->
                databaseUrlParserStrategy.getParser(DatabaseType.MONGODB).parse(url));
    }

}
