package com.example.coursesSystem.models;

public class Course {
    int courseId;
    private String name;
    private  String description;



    private double price;
    private int maxStudentsAmount;
    private int teacherId;

    public Course(){}

    public Course(String name, String description, int maxStudentsAmount, int teacherId, double price) {

        this.name = name;
        this.description = description;
        this.maxStudentsAmount = maxStudentsAmount;
        this.teacherId = teacherId;
        this.price = price;
    }

    public Course(int courseId, String name, String description, int maxStudentsAmount, int teacherId, double price) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.maxStudentsAmount = maxStudentsAmount;
        this.teacherId = teacherId;
        this.price = price;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxStudentsAmount() {
        return maxStudentsAmount;
    }

    public void setMaxStudentsAmount(int maxStudentsAmount) {
        this.maxStudentsAmount = maxStudentsAmount;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxStudentsAmount=" + maxStudentsAmount +
                ", teacheriD=" + teacherId +
                ", price=" + price +
                '}';
    }
}
