package com.example.lukaszwachowski.bakingapp.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("ingredients")
    public List<Ingredient> ingredients = null;

    @SerializedName("steps")
    public List<Step> steps = null;

    @SerializedName("servings")
    public int servings;

    @SerializedName("image")
    public String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
