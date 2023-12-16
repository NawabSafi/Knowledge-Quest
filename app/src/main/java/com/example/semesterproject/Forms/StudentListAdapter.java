package com.example.semesterproject.Forms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.semesterproject.Data.Student;
import com.example.semesterproject.R;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private FormActivity formActivity;
    private List<Student> students;
    private OnItemClickListener listener;

    public StudentListAdapter(FormActivity formActivity) {
        this.formActivity = formActivity;
    }

    public interface OnItemClickListener {
        void onUpdateClick(long itemId);

        void onDeleteClick(long itemId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentStudent = students.get(position);

        // Set the tag with the student ID or any unique identifier
        holder.itemView.setTag(currentStudent.getId());

        if (students != null) {
            holder.textViewName.setText(currentStudent.getName());
            holder.textViewRollNumber.setText(currentStudent.getRollNumber());
            holder.textViewCgpa.setText(String.valueOf(currentStudent.getCgpa()));
            holder.textViewPhoneNumber.setText(currentStudent.getPhoneNumber());
        } else {
            // Handle the case where data is not available
            holder.textViewName.setText("N/A");
            holder.textViewRollNumber.setText("N/A");
            holder.textViewCgpa.setText("N/A");
            holder.textViewPhoneNumber.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return (students != null) ? students.size() : 0;
    }

    public Student getStudentById(long itemId) {
        for (Student student : students) {
            if (student.getId() == itemId) {
                return student;
            }
        }
        return null;
    }
}
