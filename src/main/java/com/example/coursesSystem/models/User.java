package com.example.coursesSystem.models;

import java.io.Serializable;

public class User extends Person implements Serializable {
    private double balance;

    public User(){
        super();
    }

    public User(int id,String firstname, String lastname, String password, double balance){
        super(id, firstname, lastname, password);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void updateFromDb(User user){
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setPassword(user.getPassword());
    }
}
