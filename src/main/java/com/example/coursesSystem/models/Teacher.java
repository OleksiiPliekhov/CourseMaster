package com.example.coursesSystem.models;

import com.example.coursesSystem.Degree;

public class Teacher extends Person{
    private Degree qualification;
    private int experience;

    public  Teacher(){
        super();
    }

    public Teacher(int id, String firstname, String lastname, String password, Degree qualification, int experience){
        super(id, firstname, lastname, password);
        this.qualification = qualification;
        this.experience = experience;
    };

    public Degree getQualification() {
        return qualification;
    }

    public void setQualification(Degree qualification) {
        this.qualification = qualification;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


}
