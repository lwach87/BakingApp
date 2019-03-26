package com.example.lukaszwachowski.bakingapp.ui.movie;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.fragments.MovieDetailsFragment.MovieDetailsFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

  private ArrayList<Step> steps = new ArrayList<>();
  private Context context;

  public PagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public Fragment getItem(int position) {
    return MovieDetailsFragment.newInstance(steps, position);
  }

  @Override
  public int getCount() {
    return steps.size();
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return steps.get(position).shortDescription;
      default:
        return context.getString(R.string.step) + " " + position;
    }
  }

  public void swapData(List<Step> newSteps) {
    steps.addAll(newSteps);
  }
}
