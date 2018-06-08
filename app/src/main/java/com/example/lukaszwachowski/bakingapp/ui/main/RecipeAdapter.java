package com.example.lukaszwachowski.bakingapp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.DataViewHolder> {

    private List<Recipe> recipes = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    @NonNull
    @Override
    public RecipeAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recipe_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.name.setText(recipes.get(position).name);
        holder.servings.setText(String.valueOf(recipes.get(position).servings));
        holder.ingredients.setText(String.valueOf(recipes.get(position).ingredients.size()));
        holder.steps.setText(String.valueOf(recipes.get(position).steps.size()));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.servings)
        TextView servings;

        @BindView(R.id.ingredients)
        TextView ingredients;

        @BindView(R.id.steps)
        TextView steps;

        public DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(recipes.get(getAdapterPosition()));
        }
    }

    public void swapData(Recipe recipe) {
        recipes.add(recipe);
        notifyDataSetChanged();
    }
}
