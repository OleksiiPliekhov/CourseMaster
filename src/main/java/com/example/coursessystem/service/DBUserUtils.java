package com.example.coursessystem.service;

import com.example.coursessystem.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserUtils {
    public static User findUser(Connection con, String firstname, String lastname, String password) throws SQLException {
        String sql = "SELECT id, firstname, lastname, password, balance from \"user\"" +
                " where firstname = ? AND lastname = ? AND  password = ?  AND role = 'STUDENT'";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, firstname);
            pstm.setString(2, lastname);
            pstm.setString(3, password);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt("id");
                return  new User(userId, firstname, lastname, password, resultSet.getInt("balance"));
            }
            return null;
        }
    }

    public static int updateUser(Connection con, int userId, User newUser) throws SQLException {
        String sql = "UPDATE \"user\" SET firstname = ?, lastname = ?, password = ? where id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, newUser.getFirstname());
            preparedStatement.setString(2, newUser.getLastname());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setInt(4, userId);
            return preparedStatement.executeUpdate();
        }
    }

    public static int createUser(Connection con, User createdUser) throws SQLException {
        String sql = "INSERT INTO \"user\" (firstname, lastname, password, role, balance) " +
                "VALUES (?, ?, ?, 'STUDENT', 0)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, createdUser.getFirstname());
            preparedStatement.setString(2, createdUser.getLastname());
            preparedStatement.setString(3, createdUser.getPassword());

            return preparedStatement.executeUpdate();
        }
    }

    public  static  void deleteUser(Connection con, int userId) throws SQLException{
        String sql = "DELETE FROM \"user\" WHERE id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

        }
    }

}
