package com.example.semesterproject.Forms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.semesterproject.Data.Student;
import com.example.semesterproject.R;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private StudentListAdapter studentListAdapter;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        initializeRecyclerView();

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        observeStudentList();

        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(view -> showAddStudentDialog());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStudents);
        studentListAdapter = new StudentListAdapter(this);
        recyclerView.setAdapter(studentListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setRecyclerViewClickListener();
    }

    private void observeStudentList() {
        studentViewModel.getAllStudents().observe(this, students -> studentListAdapter.setStudents(students));
    }

    private void showAddStudentDialog() {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
            dialogBuilder.setView(dialogView);

            bindDialogViews(dialogView);

            alertDialog = dialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindDialogViews(View dialogView) {
        final EditText editTextRollNumber = dialogView.findViewById(R.id.editTextRollNumber);
        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextCgpa = dialogView.findViewById(R.id.editTextCgpa);
        final EditText editTextPhoneNumber = dialogView.findViewById(R.id.editTextPhoneNumber);
        Button saveButton = dialogView.findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(view -> {
            saveStudent(
                    editTextRollNumber.getText().toString(),
                    editTextName.getText().toString(),
                    editTextCgpa.getText().toString(),
                    editTextPhoneNumber.getText().toString()
            );
        });
    }
    private void showUpdateStudentDialog(Student student) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_student, null);
            dialogBuilder.setView(dialogView);

            final EditText editTextRollNumber = dialogView.findViewById(R.id.editTextRollNumber);
            final EditText editTextName = dialogView.findViewById(R.id.editTextName);
            final EditText editTextCgpa = dialogView.findViewById(R.id.editTextCgpa);
            final EditText editTextPhoneNumber = dialogView.findViewById(R.id.editTextPhoneNumber);
            Button saveButton = dialogView.findViewById(R.id.buttonSave);

            // Set existing data to the fields
            editTextRollNumber.setText(student.getRollNumber());
            editTextName.setText(student.getName());
            editTextCgpa.setText(String.valueOf(student.getCgpa()));
            editTextPhoneNumber.setText(student.getPhoneNumber());

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Check if the context is for updating or saving a new student
                    if (editTextRollNumber.getTag() != null && editTextRollNumber.getTag().equals("update")) {
                        // Handle the update logic
                        updateStudent(
                                student,
                                editTextRollNumber.getText().toString(),
                                editTextName.getText().toString(),
                                editTextCgpa.getText().toString(),
                                editTextPhoneNumber.getText().toString()
                        );
                    } else {
                        // Handle the save new student logic
                        saveStudent(
                                editTextRollNumber.getText().toString(),
                                editTextName.getText().toString(),
                                editTextCgpa.getText().toString(),
                                editTextPhoneNumber.getText().toString()
                        );
                    }
                }
            });

            // Set a tag to identify that this is for updating
            editTextRollNumber.setTag("update");

            alertDialog = dialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showDeleteConfirmationDialog(final Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this student?");
        builder.setPositiveButton("Yes", (dialog, which) -> deleteStudent(student));
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void saveStudent(String rollNumber, String name, String cgpa, String phoneNumber) {
        if (rollNumber.isEmpty() || name.isEmpty() || cgpa.isEmpty() || phoneNumber.isEmpty()) {
            showToast("All fields are required");
            return;
        }

        double cgpaValue;
        try {
            cgpaValue = Double.parseDouble(cgpa);
        } catch (NumberFormatException e) {
            showToast("Invalid CGPA format");
            return;
        }

        Student newStudent = new Student(rollNumber, name, cgpaValue, phoneNumber);
        studentViewModel.insert(newStudent);

        alertDialog.dismiss();
    }

    private void setRecyclerViewClickListener() {
        studentListAdapter.setOnItemClickListener(new StudentListAdapter.OnItemClickListener() {
            @Override
            public void onUpdateClick(long itemId) {
                onUpdateStudentClick(itemId);
            }

            @Override
            public void onDeleteClick(long itemId) {
                onDeleteStudentClick(itemId);
            }
        });
    }

    private void onUpdateStudentClick(long itemId) {
        Student student = studentListAdapter.getStudentById(itemId);
        if (student != null) {
            showUpdateStudentDialog(student);
        }
    }

    private void onDeleteStudentClick(long itemId) {
        Student student = studentListAdapter.getStudentById(itemId);
        if (student != null) {
            showDeleteConfirmationDialog(student);
        }
    }
    private void updateStudent(Student student, String rollNumber, String name, String cgpa, String phoneNumber) {
        if (rollNumber.isEmpty() || name.isEmpty() || cgpa.isEmpty() || phoneNumber.isEmpty()) {
            showToast("All fields are required");
            return;
        }

        double cgpaValue;
        try {
            cgpaValue = Double.parseDouble(cgpa);
        } catch (NumberFormatException e) {
            showToast("Invalid CGPA format");
            return;
        }

        student.setRollNumber(rollNumber);
        student.setName(name);
        student.setCgpa(cgpaValue);
        student.setPhoneNumber(phoneNumber);

        // Perform the update operation in your ViewModel
        studentViewModel.update(student);

        alertDialog.dismiss();
    }

    private void deleteStudent(Student student) {
        // Perform the delete operation in your ViewModel
        studentViewModel.delete(student);

        alertDialog.dismiss();
    }


    public void onUpdateClick(long itemId) {
        Student student = studentListAdapter.getStudentById(itemId);
        if (student != null) {
            showUpdateStudentDialog(student);
        }
    }

    public void onDeleteClick(long itemId) {
        Student student = studentListAdapter.getStudentById(itemId);
        if (student != null) {
            showDeleteConfirmationDialog(student);
        }
    }

}
