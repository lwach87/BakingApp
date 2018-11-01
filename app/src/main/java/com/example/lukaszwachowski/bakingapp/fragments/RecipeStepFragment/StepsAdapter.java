package com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.DataViewHolder> {

  private ArrayList<Step> steps = new ArrayList<>();
  private OnStepClickListener listener;
  private Context context;
  private int lastCheckedPos;
  private Picasso picasso;

  public StepsAdapter(Context context, Picasso picasso) {
    this.context = context;
    this.picasso = picasso;
  }

  public interface OnStepClickListener {

    void onItemClick(ArrayList<Step> steps, int position);
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

    holder.stepImage.setImageResource((steps.get(position).thumbnailURL.equals("")
        && steps.get(position).videoURL.equals("")) ?
        R.drawable.ic_videocam_off_black_48dp :
        R.drawable.ic_videocam_black_48dp);

    if (!steps.get(position).thumbnailURL.equals("")) {
      holder.webImage.setVisibility(View.VISIBLE);
      picasso.load(steps.get(position).thumbnailURL)
          .error(R.drawable.ic_broken_image_black_48dp)
          .into(holder.webImage);
    }

    holder.stepId.setText(position == 0 ? "" : String.valueOf(steps.get(position).id));
    holder.shortDescription.setText(steps.get(position).shortDescription);

    holder.cardView.setSelected(position == lastCheckedPos);
  }

  @Override
  public int getItemCount() {
    return steps.size();
  }

  public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.step_id)
    TextView stepId;

    @BindView(R.id.short_description)
    TextView shortDescription;

    @BindView(R.id.step_image)
    ImageView stepImage;

    @BindView(R.id.web_image)
    ImageView webImage;

    @BindView(R.id.step_card)
    CardView cardView;

    private DataViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int prevPos = lastCheckedPos;
      lastCheckedPos = getAdapterPosition();

      notifyItemChanged(prevPos);
      notifyItemChanged(lastCheckedPos);

      listener.onItemClick(steps, getAdapterPosition());
    }
  }

  public void swapData(List<Step> newSteps) {
    steps.addAll(newSteps);
    notifyDataSetChanged();
  }
}
