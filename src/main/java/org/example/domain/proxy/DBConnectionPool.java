package org.example.domain.proxy;

import java.util.LinkedList;
import java.util.Queue;

public class DBConnectionPool {
    private static volatile DBConnectionPool instance;
    private final Queue<DBConnection> pool = new LinkedList<>();

    private DBConnectionPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.add(DBConnect.newConnection());
        }
    }

    public static DBConnectionPool getInstance(int initialSize) {
        if (instance == null) {
            synchronized (DBConnectionPool.class) {
                if (instance == null) {
                    instance = new DBConnectionPool(initialSize);
                }
            }
        }
        return instance;
    }

    public DBConnection getConnection() {
        if (pool.isEmpty()) {
            System.out.println("Pool is empty, creating new connection");
            return new CachingDBConnectionProxy(DBConnect.newConnection());
        }
        return new CachingDBConnectionProxy(pool.poll());
    }

    public void returnConnectionBack(DBConnection connection) {
        pool.offer(connection);
        System.out.println("Connection returned to pool: " + connection);
    }

    public void closeAllConnections() {
        while (!pool.isEmpty()) {
            DBConnection connection = pool.poll();
            connection.close();
        }
    }
}