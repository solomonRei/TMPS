package org.example.domain.proxy;

import org.example.utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingDBConnectionProxy implements CacheableConnection {
    private final DBConnection realConnection;
    private final Map<String, List<Map<String, Object>>> queryCache;

    public CachingDBConnectionProxy(DBConnection realConnection) {
        this.realConnection = realConnection;
        this.queryCache = new HashMap<>();
    }

    @Override
    public ResultSet executeQuery(String query) {
        if (queryCache.containsKey(query)) {
            System.out.println("Returning cached result for query: " + query);
            return null;
        }

        System.out.println("Executing query and caching result: " + query);
        ResultSet resultSet = realConnection.executeQuery(query);

        try {
            if (resultSet != null) {
                List<Map<String, Object>> rows = ResultSetUtils.extractData(resultSet);
                queryCache.put(query, rows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public List<Map<String, Object>> getCachedData(String query) {
        return queryCache.get(query);
    }

    @Override
    public void close() {
        queryCache.clear();
        realConnection.close();
    }
}
