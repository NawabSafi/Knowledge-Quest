package com.example.semesterproject;// DataActivity.java
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semesterproject.R;
import com.example.semesterproject.Retrofit.DataAdapter;
import com.example.semesterproject.Retrofit.Methods;
import com.example.semesterproject.Retrofit.Model;
import com.example.semesterproject.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data from the API
        fetchData();
    }

    private void fetchData() {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    Model model = response.body();
                    if (model != null && model.getData() != null) {
                        // Set up the RecyclerView adapter
                        dataAdapter = new DataAdapter(model.getData());
                        recyclerView.setAdapter(dataAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                // Handle failure
                Toast.makeText(DataActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
