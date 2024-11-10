package org.example.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtils {

    private ResultSetUtils() {
        throw new UnsupportedOperationException("This class is not supposed to be instantiated");
    }

    public static List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();

        int columnCount = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i);
                Object value = resultSet.getObject(i);
                row.put(columnName, value);
            }
            rows.add(row);
        }

        return rows;
    }


    public static void printCachedResultSet(List<Map<String, Object>> cachedData) {
        for (Map<String, Object> row : cachedData) {
            System.out.printf(
                    "ID: %s, Name: %s, Description: %s, Price: %.2f, Currency ID: %s, Product Spec ID: %s, Created At: %s, Updated At: %s%n",
                    row.get("id"),
                    row.get("name"),
                    row.get("description"),
                    row.get("price"),
                    row.get("currency_id"),
                    row.get("product_specification_id"),
                    row.get("crt_at"),
                    row.get("upd_at")
            );
        }
    }
}