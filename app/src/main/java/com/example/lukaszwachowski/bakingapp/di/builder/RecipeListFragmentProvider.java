package com.example.lukaszwachowski.bakingapp.di.builder;

import com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment.RecipeListFragment;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment.RecipeListFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeListFragmentProvider {

    @ContributesAndroidInjector(modules = RecipeListFragmentModule.class)
    abstract RecipeListFragment provideRecipeStepFragment();
}
