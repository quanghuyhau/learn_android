package com.example.atomi.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.atomi.api.ApiService;
import com.example.atomi.api.RetrofitClient;
import com.example.atomi.api.models.User;
import com.example.atomi.api.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> userList;
    private ApiService apiService;

    public UserViewModel() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        userList = new MutableLiveData<>();
        fetchUsers();
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    private void fetchUsers() {
        Call<UserResponse> call = apiService.getUsers(20);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    userList.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }
}
