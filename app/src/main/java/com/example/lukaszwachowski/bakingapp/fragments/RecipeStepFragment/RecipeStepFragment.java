package com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;


import static com.example.lukaszwachowski.bakingapp.configuration.Constants.POSITION;
import static com.example.lukaszwachowski.bakingapp.configuration.Constants.RECIPE_OBJECT;
import static com.example.lukaszwachowski.bakingapp.configuration.Constants.STEPS_LIST;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.fragments.MovieDetailsFragment.MovieDetailsFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import com.example.lukaszwachowski.bakingapp.ui.movie.MovieActivity;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import javax.inject.Inject;

public class RecipeStepFragment extends Fragment implements StepsAdapter.OnStepClickListener {

  @BindView(R.id.ingredients_recycler_view)
  RecyclerView ingredientsRecyclerView;

  @BindView(R.id.steps_recycler_view)
  RecyclerView stepsRecyclerView;

  @BindView(R.id.ingredients_text)
  TextView ingredientsText;

  @Inject
  IngredientsAdapter ingredientsAdapter;

  @Inject
  StepsAdapter stepsAdapter;

  private Recipe recipe;

  public static RecipeStepFragment newInstance(Recipe recipe) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(RECIPE_OBJECT, recipe);
    RecipeStepFragment fragment = new RecipeStepFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidSupportInjection.inject(this);

    recipe = getArguments().getParcelable(RECIPE_OBJECT);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
    ButterKnife.bind(this, view);

    stepsAdapter.setListener(this);

    if (savedInstanceState == null) {
      ingredientsText.setVisibility(View.VISIBLE);

      ingredientsAdapter.swapData(recipe.ingredients);
      ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      ingredientsRecyclerView.setAdapter(ingredientsAdapter);

      stepsAdapter.swapData(recipe.steps);
      stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      stepsRecyclerView.setAdapter(stepsAdapter);
    }

    return view;
  }

  @Override
  public void onItemClick(ArrayList<Step> steps, int position) {

    if (getResources().getBoolean(R.bool.isTablet)) {
      getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
          .replace(R.id.fragment_movie_container, MovieDetailsFragment.newInstance(steps, position))
          .commit();
    } else {
      Intent intent = new Intent(getContext(), MovieActivity.class);
      intent.putExtra(STEPS_LIST, steps);
      intent.putExtra(POSITION, position);
      getActivity().startActivity(intent);
    }
  }
}