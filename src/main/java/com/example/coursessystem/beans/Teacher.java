package com.example.coursessystem.beans;

import com.example.coursessystem.Degree;

public class Teacher extends Person{
    private Degree degree;
    private int experienceYears;

    public  Teacher(){
        super();
    }

    public Teacher(int id, String firstname, String lastname, String password, Degree degree, int experienceYears){
        super(id, firstname, lastname, password);
        this.degree = degree;
        this.experienceYears = experienceYears;
    };

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }


}
