package com.example.semesterproject.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("DELETE FROM student_table WHERE student_name = :name")
    void deleteOne(String name);
    @Query("SELECT * FROM student_table ORDER BY student_name ASC")
    LiveData<List<Student>> getAllStudents();
    @Update
    void update(Student student);

    @Delete
    void delete(Student student);
}