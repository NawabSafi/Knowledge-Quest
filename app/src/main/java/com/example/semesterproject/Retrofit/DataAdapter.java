package com.example.semesterproject.Retrofit;

// DataAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.semesterproject.R;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final ArrayList<Model.Data> dataList;

    public DataAdapter(ArrayList<Model.Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model.Data data = dataList.get(position);

        holder.textViewName.setText("Name: " + data.getFirst_name());
        holder.textViewLastName.setText("Last Name: " + data.getLast_name());
        holder.textViewEmail.setText("Email: " + data.getEmail());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewLastName;
        TextView textViewEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            // Initialize other TextViews
        }
    }
}
