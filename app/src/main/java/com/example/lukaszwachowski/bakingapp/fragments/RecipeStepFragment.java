package com.example.lukaszwachowski.bakingapp.fragments;


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
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.RECIPE_OBJECT;

public class RecipeStepFragment extends Fragment {

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
//        setRetainInstance(true);

        DaggerRecipeStepFragmentComponent.builder()
                .recipeStepFragmentModule(new RecipeStepFragmentModule())
                .applicationComponent(BakingApp.get(getActivity()).component())
                .build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState == null) {

            stepsText.setVisibility(View.VISIBLE);
            ingredientsText.setVisibility(View.VISIBLE);

            recipe = getArguments().getParcelable(RECIPE_OBJECT);

            ingredientsAdapter.swapData(recipe.ingredients);
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ingredientsRecyclerView.setNestedScrollingEnabled(false);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);

            stepsAdapter.swapData(recipe.steps);
            stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            stepsRecyclerView.setNestedScrollingEnabled(false);
            stepsRecyclerView.setAdapter(stepsAdapter);
        }

        return view;
    }
}