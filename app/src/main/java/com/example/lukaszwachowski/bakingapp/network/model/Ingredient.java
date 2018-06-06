package com.example.lukaszwachowski.bakingapp.network.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("quantity")
    public float quantity;

    @SerializedName("measure")
    public String measure;

    @SerializedName("ingredient")
    public String ingredient;

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
