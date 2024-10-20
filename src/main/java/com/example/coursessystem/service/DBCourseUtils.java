package com.example.coursessystem.service;

import com.example.coursessystem.beans.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCourseUtils {
    public static Course findCourseByName(Connection con, String name) throws SQLException {
        String sql = "SELECT course_id, description, max_students_amount, teacher_id from course where name = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, name);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                int courseId = resultSet.getInt("course_id");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                return new Course(courseId, name, description, maxStudentsAmount, teacherId);
            }
        }
        return null;
    }

    public static Course findCourseById(Connection con, int courseId) throws SQLException {
        String sql = "SELECT name, description, max_students_amount, teacher_id from course where course_id = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, courseId);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                return new Course(courseId, name, description, maxStudentsAmount, teacherId);
            }
        }
        return null;
    }

    public static List<Course> findAllCourses(Connection con) throws SQLException {
        String sql = "SELECT course_id, name, description, max_students_amount, teacher_id from course ";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement pstm = con.prepareStatement(sql)){

            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                courses.add(new Course(courseId, name, description, maxStudentsAmount, teacherId));
            }
        }
        return courses;
    }

    public static boolean updateCourse(Connection con, Course updatedCourse) throws SQLException {
        String sql = "UPDATE course SET name = ?, description = ?, max_students_amount = ? where course.course_id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedCourse.getName());
            preparedStatement.setString(2, updatedCourse.getDescription());
            preparedStatement.setInt(3, updatedCourse.getMaxStudentsAmount());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean createCourse(Connection con, Course course) throws SQLException {
        String sql = "INSERT INTO course (name, description, max_students_amount, teacher_id) " +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getMaxStudentsAmount());
            preparedStatement.setInt(4, course.getTeacherId());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static void deleteUser(Connection con, int courseId, int userId) throws SQLException{
        String sql = "DELETE FROM course WHERE course_id = ? AND teacher_id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        }
    }

    public static boolean courseRegistration(Connection con, int course_id, int user_id) throws SQLException {
        String sqlRequest = "INSERT INTO participant VALUES (?, ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, course_id);
            preparedStatement.setInt(2, user_id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

}
