package com.example.lukaszwachowski.bakingapp.di.components;

import com.example.lukaszwachowski.bakingapp.di.MainActivityScope;
import com.example.lukaszwachowski.bakingapp.di.modules.MainActivityModule;
import com.example.lukaszwachowski.bakingapp.di.modules.RecipeServiceModule;
import com.example.lukaszwachowski.bakingapp.ui.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(modules = {MainActivityModule.class, RecipeServiceModule.class},
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
