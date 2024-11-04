package com.example.coursesSystem;
import java.sql.*;

public class DBConnection {
    public static Connection initDBConnection() throws SQLException,ClassNotFoundException{
        String dbDriver = "org.postgresql.Driver";
        String dbURL = "jdbc:postgresql://localhost:5432/";
        String dbUsername = "postgres";
        String dbName = "courses_system";
        String dbPassword = "admin";

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
    }
}
