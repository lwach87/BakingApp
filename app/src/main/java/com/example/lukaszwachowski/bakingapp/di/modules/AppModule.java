package com.example.lukaszwachowski.bakingapp.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.lukaszwachowski.bakingapp.network.RecipeService;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Provides
    @Singleton
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    RecipeService recipeService(Retrofit retrofit) {
        return retrofit.create(RecipeService.class);
    }
}
