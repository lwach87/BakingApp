package com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.BakingApp;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.di.components.DaggerRecipeStepFragmentComponent;
import com.example.lukaszwachowski.bakingapp.di.modules.RecipeStepFragmentModule;
import com.example.lukaszwachowski.bakingapp.fragments.MovieDetailsFragment.MovieDetailsFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.ui.detail.DetailActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.RECIPE_OBJECT;

public class RecipeStepFragment extends Fragment implements StepsAdapter.OnStepClickListener {

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRecyclerView;

    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;

    @BindView(R.id.ingredients_text)
    TextView ingredientsText;

    @BindView(R.id.steps_text)
    TextView stepsText;

    @Inject
    IngredientsAdapter ingredientsAdapter;

    @Inject
    StepsAdapter stepsAdapter;

    @Inject
    DetailActivity activity;

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

        DaggerRecipeStepFragmentComponent.builder()
                .recipeStepFragmentModule(new RecipeStepFragmentModule(((DetailActivity) getContext())))
                .applicationComponent(BakingApp.get(getActivity()).component())
                .build().inject(this);

        recipe = getArguments().getParcelable(RECIPE_OBJECT);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);

        stepsAdapter.setListener(this);

        if (savedInstanceState == null) {
            stepsText.setVisibility(View.VISIBLE);
            ingredientsText.setVisibility(View.VISIBLE);

            ingredientsAdapter.swapData(recipe.ingredients);
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            ingredientsRecyclerView.setNestedScrollingEnabled(false);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);

            stepsAdapter.swapData(recipe);
            stepsRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            stepsRecyclerView.setNestedScrollingEnabled(false);
            stepsRecyclerView.setAdapter(stepsAdapter);
        }

        return view;
    }

    @Override
    public void onItemClick(Recipe recipe, int position) {

        if (getResources().getBoolean(R.bool.isTablet)) {
            activity.getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(R.id.fragment_movie_container, MovieDetailsFragment.newInstance(recipe, position))
                    .commit();
        }
    }
}