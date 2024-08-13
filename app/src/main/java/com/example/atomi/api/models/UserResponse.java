package com.example.atomi.api.models;

import androidx.databinding.Bindable;

import java.util.List;

public class UserResponse {
    private List<User> results;
    @Bindable
    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
