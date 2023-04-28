package com.example.cs437;

import java.util.List;

public class FoodItem {
    String name;
    int expiry;
    int addDate;
    String foodImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public int getAddDate() {
        return addDate;
    }

    public void setAddDate(int addDate) {
        this.addDate = addDate;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public FoodItem(String name, int expiry, int addDate, String foodImage) {
        this.name = name;
        this.expiry = expiry;
        this.addDate = addDate;
        this.foodImage = foodImage;
    }
}
