package com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.DataViewHolder> {

    private Recipe recipe;
    private OnStepClickListener listener;
    private Context context;

    public StepsAdapter(Context context) {
        this.context = context;
    }

    public interface OnStepClickListener {
        void onItemClick(Recipe recipe, int position);
    }

    public void setListener(OnStepClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.steps_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        if (recipe.steps.get(position).thumbnailURL.equals("") && recipe.steps.get(position).videoURL.equals("")) {
            holder.stepImage.setImageResource(R.drawable.ic_videocam_off_black_48dp);
        } else {
            holder.stepImage.setImageResource(R.drawable.ic_videocam_black_48dp);
        }

        holder.stepId.setText(String.valueOf(recipe.steps.get(position).id + 1));
        holder.shortDescription.setText(recipe.steps.get(position).shortDescription);
    }

    @Override
    public int getItemCount() {
        return recipe.steps.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.step_id)
        TextView stepId;

        @BindView(R.id.short_description)
        TextView shortDescription;

        @BindView(R.id.step_image)
        ImageView stepImage;

        public DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(recipe, getAdapterPosition());
        }
    }

    public void swapData(Recipe newRecipe) {
        recipe = newRecipe;
        notifyDataSetChanged();
    }
}
