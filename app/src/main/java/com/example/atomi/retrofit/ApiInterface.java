package com.example.atomi.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<MainNews> getNews(
            @Query("q") String q,
            @Query("pageSize") int pagesize,
            @Query("apikey") String apikey
    );


    @GET("top-headlines")
    Call<MainNews> getCategory(
            @Query("q") String q,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apikey") String apikey
            );




}
