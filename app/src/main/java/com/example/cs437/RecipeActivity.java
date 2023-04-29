package com.example.cs437;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        setTitle("YOUR RECIPES");

        ArrayList<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe("Fruit Salad", R.drawable.salad, "Toss the fruit together in a large serving bowl. Combine the honey, orange and lime juice, and lime zest, whisk to blend. Pour honey-lime dressing over the fruit. Toss to coat, and serve immediately."));
        recipes.add(new Recipe("Fruit Salad", R.drawable.salad, "Toss the fruit together in a large serving bowl. Combine the honey, orange and lime juice, and lime zest, whisk to blend. Pour honey-lime dressing over the fruit. Toss to coat, and serve immediately."));

        recyclerView = (RecyclerView)findViewById(R.id.recipe_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, this);
        recyclerView.setAdapter(recipeAdapter);
    }
}

