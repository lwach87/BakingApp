package com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.DataViewHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientsAdapter() {
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.quantity.setText(String.valueOf(ingredients.get(position).quantity));
        holder.measure.setText(ingredients.get(position).measure);
        holder.ingredient.setText(ingredients.get(position).ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quantity)
        TextView quantity;

        @BindView(R.id.measure)
        TextView measure;

        @BindView(R.id.ingredient)
        TextView ingredient;

        private DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void swapData(List<Ingredient> ingredientsList) {
        ingredients.addAll(ingredientsList);
        notifyDataSetChanged();
    }
}
