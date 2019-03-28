package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeListFragmentModule {

    @Provides
    public RecipeAdapter recipeAdapter() {
        return new RecipeAdapter();
    }
}
