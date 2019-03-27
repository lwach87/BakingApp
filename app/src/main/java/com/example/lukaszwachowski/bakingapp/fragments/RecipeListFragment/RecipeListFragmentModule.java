package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;

import com.example.lukaszwachowski.bakingapp.ui.main.RecipeAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeListFragmentModule {

    @Provides
    public RecipeAdapter recipeAdapter() {
        return new RecipeAdapter();
    }
}
