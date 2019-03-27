package com.example.lukaszwachowski.bakingapp.di.builder;

import com.example.lukaszwachowski.bakingapp.di.modules.RecipeStepFragmentModule;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.RecipeStepFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecipeStepFragmentProvider {

    @ContributesAndroidInjector(modules = RecipeStepFragmentModule.class)
    abstract RecipeStepFragment provideRecipeStepFragment();
}
