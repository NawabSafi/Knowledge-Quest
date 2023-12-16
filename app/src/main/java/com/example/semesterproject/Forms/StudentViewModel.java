package com.example.semesterproject.Forms;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.semesterproject.Data.Student;
import com.example.semesterproject.Data.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel(Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        allStudents = studentRepository.getAllStudents();
    }

    public void update(Student student) {
        studentRepository.update(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insert(Student student) {
        studentRepository.insert(student);
    }
}
