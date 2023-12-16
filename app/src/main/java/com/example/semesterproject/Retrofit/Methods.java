package com.example.semesterproject.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
public interface Methods {

    @GET("users?page=2")
    Call<Model> getAllData();

}
