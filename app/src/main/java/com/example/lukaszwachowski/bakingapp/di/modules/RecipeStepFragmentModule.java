package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.IngredientsAdapter;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.StepsAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeStepFragmentModule {

    @Provides
    public IngredientsAdapter ingredientsAdapter() {
        return new IngredientsAdapter();
    }

    @Provides
    public StepsAdapter stepsAdapter(Picasso picasso) {
        return new StepsAdapter(picasso);
    }
}
