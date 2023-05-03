package com.example.cs437;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FoodItem> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().setLenient().create();

//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("FB", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        System.out.println("The toke is " + token);
//
//                    }
//                });



        foodItems = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.10.47:5000/init")
                    .build();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response responses = null;
                        responses = client.newCall(request).execute();
                        String jsonData = responses.body().string();
                        JSONObject Jobject = new JSONObject(jsonData);
                        JSONArray Jarray = Jobject.getJSONArray("results");

                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject object = Jarray.getJSONObject(i);
                            System.out.println(object);
                            FoodItem foodItem = gson.fromJson(object.toString(), FoodItem.class);
                            foodItems.add(foodItem);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.start();
            t.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(foodItems);
        recyclerView = (RecyclerView)findViewById(R.id.food_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        new Server(5000, foodItems, recyclerView, this).start();

        FloatingActionButton whatToCook = findViewById(R.id.fab);
        whatToCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RecipeActivity.class);

                ArrayList<String> foodItemNames = new ArrayList<>();
                for(FoodItem foodItem: foodItems){
                    foodItemNames.add(foodItem.getName());
                }
                i.putStringArrayListExtra("foodItemNames", foodItemNames);
                startActivity(i);
            }
        });

    }
}