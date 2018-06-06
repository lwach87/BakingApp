package com.example.lukaszwachowski.bakingapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import javax.inject.Inject;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
}
