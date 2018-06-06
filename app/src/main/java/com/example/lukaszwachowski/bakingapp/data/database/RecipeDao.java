package com.example.lukaszwachowski.bakingapp.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.lukaszwachowski.bakingapp.network.model.Ingredient;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.network.model.Step;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getRecipes();

    @Insert(onConflict = REPLACE)
    void insertRecipes(Recipe recipe);

//    @Query("SELECT * FROM steps WHERE recipeId = :recipeId")
//    LiveData<List<Ingredient>> getIngredients(int recipeId);
//
//    @Insert(onConflict = REPLACE)
//    void insertIngredients(Ingredient ingredient);

//    @Query("SELECT * FROM steps WHERE recipeId = :recipeId")
//    LiveData<List<Step>> getSteps(int recipeId);
//
//    @Insert(onConflict = REPLACE)
//    void insertSteps(Step step);
}
