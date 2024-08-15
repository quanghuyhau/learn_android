package com.example.atomi.models;

public class AllProductModel {
    String image;
    String description;
    String name;
    String rating;
    String price;

    public AllProductModel() {
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AllProductModel(String image, String description, String name, String rating, String price) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }
}
