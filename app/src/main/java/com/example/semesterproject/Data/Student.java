package com.example.semesterproject.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//complete this class
@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "student_name")
    private String name;

    @ColumnInfo(name = "student_roll_number")
    private String rollNumber;

    @ColumnInfo(name = "student_cgpa")
    private double cgpa;

    @ColumnInfo(name = "student_phone_number")
    private String phoneNumber;

    public Student(String name, String rollNumber, double cgpa, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.cgpa = cgpa;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}