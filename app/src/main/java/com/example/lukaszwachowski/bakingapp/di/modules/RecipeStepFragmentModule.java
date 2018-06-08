package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.RecipeStepFragmentScope;
import com.example.lukaszwachowski.bakingapp.fragments.IngredientsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeStepFragmentModule {

    @Provides
    @RecipeStepFragmentScope
    public IngredientsAdapter ingredientsAdapter() {
        return new IngredientsAdapter();
    }
}
