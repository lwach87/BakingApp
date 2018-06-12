package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.MainActivityScope;
import com.example.lukaszwachowski.bakingapp.ui.main.MainActivity;
import com.example.lukaszwachowski.bakingapp.ui.main.RecipeAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }

    @Provides
    @MainActivityScope
    public RecipeAdapter recipeAdapter() {
        return new RecipeAdapter(mainActivity);
    }
}
