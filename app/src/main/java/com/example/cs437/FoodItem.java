package com.example.cs437;

import java.util.List;

public class FoodItem {
    String name;
    long expiry;
    long addDate;
    String foodImage;
    String direction;

    public void setDirection(String direction){this.direction = direction;}

    public String getDirection(){
        return direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public long getAddDate() {
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

    public FoodItem(String name, long expiry, long addDate, String foodImage, String direction) {
        this.name = name;
        this.expiry = expiry;
        this.addDate = addDate;
        this.foodImage = foodImage;
        this.direction = direction;
    }
}
