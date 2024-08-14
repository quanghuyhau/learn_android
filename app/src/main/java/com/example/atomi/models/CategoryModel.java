package com.example.atomi.models;

public class CategoryModel {
    String image;
    String name;

    String type;

    public CategoryModel() {
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CategoryModel(String image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
    }
}
