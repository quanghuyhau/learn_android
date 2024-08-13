package com.example.atomi.api;

import com.example.atomi.api.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/")
    Call<UserResponse> getUsers(@Query("results") int results);
}

