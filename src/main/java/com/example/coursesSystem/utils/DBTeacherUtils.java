package com.example.coursesSystem.utils;

import com.example.coursesSystem.Degree;
import com.example.coursesSystem.models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTeacherUtils {
    public static Teacher findTeacherById(Connection con, int teacherId) throws SQLException {
        String sql = "SELECT u.firstname, u.lastname, u.password, t.qualification, t.experience FROM \"user\" u  INNER JOIN teacher t on t.user_id = u.id" +
                " where id = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, teacherId);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                String degreeStr = resultSet.getString("qualification");
                int experienceYears = resultSet.getInt("experience");
                Degree degree = Degree.valueOf(degreeStr);

                return  new Teacher(teacherId, firstname, lastname, password, degree, experienceYears);
            }
        }
        return null;
    }

    public static Teacher findTeacher(Connection con, String firstname, String lastname, String password) throws SQLException {
        String sql = "SELECT u.firstname, u.lastname, u.password, t.qualification, t.experience FROM \"user\" u  INNER JOIN teacher t on t.user_id = u.id" +
                " where firstname = ? AND lastname = ? AND  password = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, firstname);
            pstm.setString(2, lastname);
            pstm.setString(3, password);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                int teacherId = resultSet.getInt("teacher_id");
                String degreeStr =  resultSet.getString("qualification");
                int experienceYears = resultSet.getInt("experience");
                Degree degree = Degree.valueOf(degreeStr);

                return  new Teacher(teacherId, firstname, lastname, password, degree, experienceYears);
            }
        }
        return null;
    }

    public static boolean updateTeacher(Connection con, int teacherId, Teacher updatedTeacher) throws SQLException {
        String sql = "UPDATE \"user\" SET firstname = ?, lastname = ?, password = ? where id = ?";
        String sqlRequest = "UPDATE teacher SET qualification = ?, experience = ? where user_id = ?";
        int res;
        try(PreparedStatement preparedStatement = con.prepareStatement(sql); PreparedStatement preparedStatement2 = con.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, updatedTeacher.getFirstname());
            preparedStatement.setString(2, updatedTeacher.getLastname());
            preparedStatement.setString(3, updatedTeacher.getPassword());
            preparedStatement.setInt(4, teacherId);
            res = preparedStatement.executeUpdate();

            preparedStatement2.setString(1, updatedTeacher.getQualification().name().toUpperCase());
            preparedStatement2.setInt(2, updatedTeacher.getExperience());
            preparedStatement2.setInt(3, teacherId);
            res += preparedStatement2.executeUpdate();
        }

        return res > 0;
    }

    public static boolean createTeacher(Connection con, Teacher createdTeacher) throws SQLException {
        String sql = "INSERT INTO \"user\" (firstname, lastname, password, balance) " +
                "VALUES (?, ?, ?, 0)";

        String sqlRequest = "INSERT INTO teacher (user_id, qualification, experience)" +
                "VALUES (?, ?, ?)";

        int res = 0;
        try(PreparedStatement preparedStatement = con.prepareStatement(sql); PreparedStatement preparedStatement2 = con.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, createdTeacher.getFirstname());
            preparedStatement.setString(2, createdTeacher.getLastname());
            preparedStatement.setString(3, createdTeacher.getPassword());

            res =  preparedStatement.executeUpdate();
            preparedStatement2.setString(1, createdTeacher.getQualification().toString().toUpperCase());
            preparedStatement2.setInt(2, createdTeacher.getExperience());

            res += preparedStatement2.executeUpdate();
        }
        return res > 0;
    }

    public  static  void deleteUser(Connection con, int teacherId) throws SQLException{
        /*Teacher teacher;
        try{
           teacher = findTeacher(con, firstname, lastname, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        String sql = "DELETE FROM \"user\" WHERE id = ? ";
        String sqlRequest = "DELETE FROM teacher WHERE user_id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql); PreparedStatement preparedStatement2 = con.prepareStatement(sqlRequest)){
            //preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setInt(1, teacherId);

            preparedStatement.executeUpdate();

            //preparedStatement2.setInt(1, teacher.getId());
            preparedStatement2.setInt(1, teacherId);
            preparedStatement2.executeUpdate();
        }
    }

    public static boolean isTeacher(Connection con, int userId) throws SQLException {

        String sqlRequest = "SELECT EXISTS (SELECT 1 FROM teacher WHERE user_id = ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                 return resultSet.getBoolean(1);
            }
        }
        return false;
    }
}
