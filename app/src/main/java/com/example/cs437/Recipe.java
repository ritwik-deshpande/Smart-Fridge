package com.example.cs437;

public class Recipe {
    String name;
    int image;
    String desciption;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Recipe(String name, int image, String desciption) {
        this.name = name;
        this.image = image;
        this.desciption = desciption;
    }
}
