package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.RecipeStepFragmentScope;
import com.example.lukaszwachowski.bakingapp.fragments.IngredientsAdapter;
import com.example.lukaszwachowski.bakingapp.fragments.StepsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeStepFragmentModule {

    @Provides
    @RecipeStepFragmentScope
    public IngredientsAdapter ingredientsAdapter() {
        return new IngredientsAdapter();
    }

    @Provides
    @RecipeStepFragmentScope
    public StepsAdapter stepsAdapter() {
        return new StepsAdapter();
    }
}
