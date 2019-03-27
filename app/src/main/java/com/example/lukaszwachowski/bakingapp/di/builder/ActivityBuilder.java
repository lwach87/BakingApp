package com.example.lukaszwachowski.bakingapp.di.builder;

import com.example.lukaszwachowski.bakingapp.di.modules.MovieActivityModule;
import com.example.lukaszwachowski.bakingapp.ui.main.MainActivity;
import com.example.lukaszwachowski.bakingapp.ui.movie.MovieActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {RecipeListFragmentProvider.class,
            RecipeStepFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = MovieActivityModule.class)
    abstract MovieActivity bindMovieActivity();
}
