package com.example.lukaszwachowski.bakingapp.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.DataViewHolder> {

    private List<Step> steps = new ArrayList<>();

    public StepsAdapter() {
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        if (steps.get(position).thumbnailURL.equals("") && steps.get(position).videoURL.equals("")) {
            holder.stepImage.setImageResource(R.drawable.ic_videocam_off_black_48dp);
        } else {
            holder.stepImage.setImageResource(R.drawable.ic_videocam_black_48dp);
        }

        holder.stepId.setText(String.valueOf(steps.get(position).id + 1));
        holder.shortDescription.setText(steps.get(position).shortDescription);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_id)
        TextView stepId;

        @BindView(R.id.short_description)
        TextView shortDescription;

        @BindView(R.id.step_image)
        ImageView stepImage;

        public DataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void swapData(List<Step> stepList) {
        steps.addAll(stepList);
        notifyDataSetChanged();
    }
}
