package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.MovieActivityScope;
import com.example.lukaszwachowski.bakingapp.ui.movie.MovieActivity;
import com.example.lukaszwachowski.bakingapp.ui.movie.PagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieActivityModule {

    private MovieActivity movieActivity;

    public MovieActivityModule(MovieActivity movieActivity) {
        this.movieActivity = movieActivity;
    }

    @Provides
    @MovieActivityScope
    public PagerAdapter pagerAdapter() {
        return new PagerAdapter(movieActivity.getSupportFragmentManager(), movieActivity);
    }
}
