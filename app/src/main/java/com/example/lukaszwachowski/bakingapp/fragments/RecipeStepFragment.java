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

    @Inject
    IngredientsAdapter ingredientsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRecipeStepFragmentComponent.builder()
                .recipeStepFragmentModule(new RecipeStepFragmentModule())
                .applicationComponent(BakingApp.get(getActivity()).component())
                .build().inject(this);
    }

    public static RecipeStepFragment newInstance(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_OBJECT, recipe);
        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();

        Recipe recipe = arguments.getParcelable(RECIPE_OBJECT);

        ingredientsAdapter.swapData(recipe.ingredients);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        return view;
    }
}