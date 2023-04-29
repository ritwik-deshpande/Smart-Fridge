package com.example.cs437;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FoodItem> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("YOUR SMART INVENTORY");

        recyclerView = (RecyclerView)findViewById(R.id.food_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        foodItems = new ArrayList<>();
        new Server(5000, foodItems, recyclerView, this).start();

        FloatingActionButton whatToCook = findViewById(R.id.fab);
        whatToCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RecipeActivity.class);
                startActivity(i);
            }
        });

    }
}