package com.example.lukaszwachowski.bakingapp.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.lukaszwachowski.bakingapp.data.database.RecipeDao;
import com.example.lukaszwachowski.bakingapp.data.database.RecipeDatabase;
import com.example.lukaszwachowski.bakingapp.data.database.Repository;
import com.example.lukaszwachowski.bakingapp.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module
public class DatabaseModule {

    @Provides
    @ApplicationScope
    public RecipeDatabase moviesDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                RecipeDatabase.class, "recipes").build();
    }

    @Provides
    @ApplicationScope
    RecipeDao provideDao(RecipeDatabase recipeDatabase) {
        return recipeDatabase.recipeDao();
    }

    @Provides
    @ApplicationScope
    Repository provideRepository(RecipeDao recipeDao) {
        return new Repository(recipeDao);
    }
}
