package com.example.cs437;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Recipe> recipes;


    public CompletableFuture<ArrayList<Recipe>> updateRecipes(String foodName) throws InterruptedException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Recipe> recipes = new ArrayList<>();
        CompletableFuture<ArrayList<Recipe>> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/complexSearch?query="+ foodName +"&apiKey=8ca624867a1e43e8ad99e6e9cedae019")
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
                        String name = (String) object.get("title");
                        String image = (String)object.get("image");
                        Recipe recipe = new Recipe(name, image, "");
                        recipes.add(recipe);
                    }
                    future.complete(recipes);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
        return future;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setTitle("YOUR RECIPES");

        ArrayList<String> foodItemNames = getIntent().getStringArrayListExtra("foodItemNames");
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            List<CompletableFuture<ArrayList<Recipe>>> futuresList = new ArrayList<>();
            for (String foodItemName : foodItemNames ){
                futuresList.add(updateRecipes(foodItemName));
            }
            CompletableFuture<Void> a = CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
            a.join();
            if(a.isDone()){
                for (CompletableFuture<ArrayList<Recipe>> recipesFuture: futuresList){
                    if(!recipesFuture.isCompletedExceptionally()){
                        ArrayList<Recipe> new_recipes = recipesFuture.get();
                        recipes.addAll(new_recipes);
                    }
                }
            }
            recyclerView = (RecyclerView)findViewById(R.id.recipe_recyclerview);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, this);
            recyclerView.setAdapter(recipeAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

