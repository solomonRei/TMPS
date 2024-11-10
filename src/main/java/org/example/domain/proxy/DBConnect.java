package org.example.domain.proxy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect implements DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/lab2";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private Connection connection;

    public DBConnect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is established: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnect newConnection() {
        return new DBConnect();
    }

    @Override
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

