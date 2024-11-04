package com.example.coursesSystem.repositories;

import com.example.coursesSystem.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBUserUtils {
    public static User findUserById(Connection con, int userId) throws SQLException {
        String sql = "SELECT  firstname, lastname, password, balance from \"user\"" +
                " where id = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, userId);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                int balance = resultSet.getInt("balance");
                return  new User(userId, firstname, lastname, password, balance);
            }
            return null;
        }
    }

    public static User findUser(Connection con, String firstname, String lastname, String password) throws SQLException {
        String sql = "SELECT id, firstname, lastname, password, balance from \"user\"" +
                " where firstname = ? AND lastname = ? AND  password = ?";

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

    public static boolean updateUser(Connection con, int userId, User newUser) throws SQLException {
        String sql = "UPDATE \"user\" SET firstname = ?, lastname = ?, password = ? where id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, newUser.getFirstname());
            preparedStatement.setString(2, newUser.getLastname());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setInt(4, userId);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static void createUser(Connection con, User createdUser) throws SQLException {
        String sql = "INSERT INTO \"user\" (firstname, lastname, password, balance) " +
                "VALUES (?, ?, ?, 0)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, createdUser.getFirstname());
            preparedStatement.setString(2, createdUser.getLastname());
            preparedStatement.setString(3, createdUser.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static  void deleteUser(Connection con, int userId) throws SQLException{
        String sql = "DELETE FROM \"user\" WHERE id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();

        }
    }

    public static  int findMaxUserId(Connection con) throws SQLException{
        String sqlQuery = "SELECT COUNT(id) as count_id FROM \"user\"";
        try(PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("count_id");            }
        }
        return 0;
    }

}
