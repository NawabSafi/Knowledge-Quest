// StudentViewHolder.java
package com.example.semesterproject.Forms;

import static com.example.semesterproject.Forms.Utils.showToast;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.semesterproject.Data.Student;
import com.example.semesterproject.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    final TextView textViewName;
    final TextView textViewRollNumber;
    final TextView textViewCgpa;
    final TextView textViewPhoneNumber;
    final Button buttonUpdate;
    final Button buttonDelete;

    public StudentViewHolder(View itemView, StudentListAdapter.OnItemClickListener listener) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewRollNumber = itemView.findViewById(R.id.textViewRollNumber);
        textViewCgpa = itemView.findViewById(R.id.textViewCgpa);
        textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        buttonDelete = itemView.findViewById(R.id.buttonDelete);

        setClickListeners(listener);
    }

    public void bind(Student student) {
        itemView.setTag(student.getId());

        textViewName.setText(student.getName());
        textViewRollNumber.setText(student.getRollNumber());
        textViewCgpa.setText(String.valueOf(student.getCgpa()));
        textViewPhoneNumber.setText(student.getPhoneNumber());
    }

    private void setClickListeners(StudentListAdapter.OnItemClickListener listener) {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag = itemView.getTag();
                if (tag != null) {
                    try {
                        long itemId;
                        if (tag instanceof Long) {
                            itemId = (long) tag;
                        } else if (tag instanceof Integer) {
                            itemId = ((Integer) tag).longValue();
                        } else {
                            // Log the issue for debugging
                            Utils.showToast(itemView.getContext(), "Invalid item ID");
                            return;
                        }

                        listener.onUpdateClick(itemId);
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                        Utils.showToast(itemView.getContext(), "Error processing item ID");
                    }
                } else {
                    Utils.showToast(itemView.getContext(), "Item ID is null");
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag = itemView.getTag();
                if (tag != null) {
                    try {
                        long itemId;
                        if (tag instanceof Long) {
                            itemId = (long) tag;
                        } else if (tag instanceof Integer) {
                            itemId = ((Integer) tag).longValue();
                        } else {
                            // Log the issue for debugging
                            Utils.showToast(itemView.getContext(), "Invalid item ID");
                            return;
                        }

                        listener.onDeleteClick(itemId);
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                        Utils.showToast(itemView.getContext(), "Error processing item ID");
                    }
                } else {
                    Utils.showToast(itemView.getContext(), "Item ID is null");
                }
            }
        });
    }

}
