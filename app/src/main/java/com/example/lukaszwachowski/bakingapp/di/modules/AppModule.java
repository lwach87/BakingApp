package com.example.lukaszwachowski.bakingapp.di.modules;

import android.app.Application;
import android.content.Context;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  @Provides
  @Singleton
  public Context context(Application application) {
    return application;
  }

  @Provides
  @Singleton
  public Picasso picasso(Context context) {
    return new Picasso.Builder(context).build();
  }
}
