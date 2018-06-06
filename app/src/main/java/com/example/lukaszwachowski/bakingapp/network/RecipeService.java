package com.example.lukaszwachowski.bakingapp.network;

import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
