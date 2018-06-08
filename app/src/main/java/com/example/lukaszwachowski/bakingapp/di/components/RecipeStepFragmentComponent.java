package com.example.lukaszwachowski.bakingapp.di.components;

import com.example.lukaszwachowski.bakingapp.di.RecipeStepFragmentScope;
import com.example.lukaszwachowski.bakingapp.di.modules.RecipeStepFragmentModule;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;

import dagger.Component;

@RecipeStepFragmentScope
@Component(modules = {RecipeStepFragmentModule.class},
        dependencies = ApplicationComponent.class)
public interface RecipeStepFragmentComponent {
    void inject(RecipeStepFragment fragment);
}
