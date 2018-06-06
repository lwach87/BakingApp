package com.example.lukaszwachowski.bakingapp.data.database;

import android.arch.lifecycle.LiveData;

import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import java.util.List;

public class Repository {

    private RecipeDao recipeDao;

    public Repository(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public void insertRecipes(Recipe recipe) {
        recipeDao.insertRecipes(recipe);
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeDao.getRecipes();
    }

//    public LiveData<List<Ingredient>> getIngredientsById(int recipeId) {
//        return recipeDao.getIngredients(recipeId);
//    }
//
//    public LiveData<List<Step>> getStepsById(int recipeId) {
//        return recipeDao.getSteps(recipeId);
//    }
}
