package com.onlineordersystem.core.database;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // Kết nối giả lập
        System.out.println("Database connection established.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}