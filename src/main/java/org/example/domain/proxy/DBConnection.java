package org.example.domain.proxy;

import java.sql.ResultSet;

public interface DBConnection {
    ResultSet executeQuery(String query);

    void close();
}
