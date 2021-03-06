package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.DataViewHolder> {

    private List<Recipe> recipes = new ArrayList<>();
    private OnItemClickListener listener;

    public RecipeAdapter() {
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
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

    public void swapData(Recipe recipe) {
        recipes.add(recipe);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {

        void onItemClick(Recipe recipe);
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

        private DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(recipes.get(getAdapterPosition()));
        }
    }
}
