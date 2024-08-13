package com.example.atomi.api.models;

import androidx.databinding.Bindable;

public class Location {
    private String city;
    private String country;

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Bindable

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
