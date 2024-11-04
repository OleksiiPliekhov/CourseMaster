package com.example.coursesSystem.repositories;

import com.example.coursesSystem.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCourseUtils {
    public static Course findCourseByName(Connection con, String name) throws SQLException {
        String sql = "SELECT course_id, description, max_students_amount, teacher_id, price from course where name = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1, name);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                int courseId = resultSet.getInt("course_id");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                double price = resultSet.getDouble("price");
                return new Course(courseId, name, description, maxStudentsAmount, teacherId, price);
            }
        }
        return null;
    }

    public static Course findCourseById(Connection con, int courseId) throws SQLException {
        String sql = "SELECT name, description, max_students_amount, teacher_id, price from course where course_id = ?";

        try(PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setInt(1, courseId);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                double price = resultSet.getDouble("price");
                return new Course(courseId, name, description, maxStudentsAmount, teacherId, price);
            }
        }
        return null;
    }

    public static List<Course> findAllCourses(Connection con) throws SQLException {
        String sql = "SELECT course_id, name, description, max_students_amount, teacher_id, price from course ";
        List<Course> courses = new ArrayList<>();
        try(PreparedStatement pstm = con.prepareStatement(sql)){

            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int maxStudentsAmount = resultSet.getInt("max_students_amount");
                int teacherId = resultSet.getInt("teacher_id");
                double price = resultSet.getDouble("price");
                courses.add(new Course(courseId, name, description, maxStudentsAmount, teacherId, price));
            }
        }
        return courses;
    }

    public static boolean updateCourse(Connection con, int courseId, Course updatedCourse) throws SQLException {
        String sql = "UPDATE course SET name = ?, description = ?, max_students_amount = ?, price = ? where course.course_id = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedCourse.getName());
            preparedStatement.setString(2, updatedCourse.getDescription());
            preparedStatement.setInt(3, updatedCourse.getMaxStudentsAmount());
            preparedStatement.setDouble(4, updatedCourse.getPrice());
            preparedStatement.setInt(5, courseId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static boolean createCourse(Connection con, Course course) throws SQLException {
        String sql = "INSERT INTO course (name, description, max_students_amount, teacher_id, price) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getMaxStudentsAmount());
            preparedStatement.setInt(4, course.getTeacherId());
            preparedStatement.setDouble(5, course.getPrice());

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

    public static void registerOnCourse(Connection con, int courseId, int userId) throws SQLException {
        String sqlRequest = "INSERT INTO participant VALUES (?, ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(sqlRequest)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        }
    }

    public static List<Course> getUserCourses(Connection con, int userId) throws SQLException {
        List<Course> courses = new ArrayList<>();

        String query = """
                SELECT c.course_id, c.name, c.description, c.max_students_amount, c.teacher_id, c.price FROM course c
                JOIN participant p ON c.course_id = p.user_id WHERE p.user_id = ?
                """;

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setName(rs.getString("name"));
                    course.setDescription(rs.getString("description"));
                    course.setMaxStudentsAmount(rs.getInt("max_students_amount"));
                    course.setTeacherId(rs.getInt("teacher_id"));
                    course.setPrice(rs.getDouble("price"));
                    courses.add(course);
                }
            }
        }
        return courses;
    }
}
