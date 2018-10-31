package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.ui.movie.MovieActivity;
import com.example.lukaszwachowski.bakingapp.ui.movie.PagerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieActivityModule {

  @Provides
  public PagerAdapter pagerAdapter(MovieActivity movieActivity) {
    return new PagerAdapter(movieActivity.getSupportFragmentManager(), movieActivity);
  }
}
