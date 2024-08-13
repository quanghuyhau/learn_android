package com.example.atomi.api.models;

import androidx.databinding.Bindable;

public class Name {
    private String first;
    private String last;
    @Bindable
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    @Bindable
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
