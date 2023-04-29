package com.example.cs437;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    private ArrayList<Recipe> recipes = new ArrayList<>();
    Context context;

    public RecipeAdapter(ArrayList<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.recipe_item, viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder myViewHolder, final int i) {
        Glide.with(context).asBitmap().load(recipes.get(i).getImage()).into(myViewHolder.image);
        myViewHolder.name.setText(recipes.get(i).getName());
        myViewHolder.deccription.setText(String.valueOf(recipes.get(i).getDesciption()));
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView deccription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.recipe_img);
            name= itemView.findViewById(R.id.recipe_name);
            deccription= itemView.findViewById(R.id.recipe_description);
        }
    }
}
