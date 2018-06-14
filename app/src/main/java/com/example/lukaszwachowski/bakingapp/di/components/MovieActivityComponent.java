package com.example.lukaszwachowski.bakingapp.di.components;

import com.example.lukaszwachowski.bakingapp.di.MovieActivityScope;
import com.example.lukaszwachowski.bakingapp.di.modules.MovieActivityModule;
import com.example.lukaszwachowski.bakingapp.ui.movie.MovieActivity;

import dagger.Component;

@MovieActivityScope
@Component(modules = {MovieActivityModule.class},
        dependencies = ApplicationComponent.class)
public interface MovieActivityComponent {
    void inject(MovieActivity movieActivity);
}
