package com.example.lukaszwachowski.bakingapp.network.model;

import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "ingredients")
public class Ingredient {

    //    @PrimaryKey
    public int recipeId;

    @SerializedName("quantity")
    public int quantity;

    @SerializedName("measure")
    public String measure;

    @SerializedName("ingredient")
    public String ingredient;

//    public Ingredient(int recipeId, int quantity, String measure, String ingredient) {
//        this.recipeId = recipeId;
//        this.quantity = quantity;
//        this.measure = measure;
//        this.ingredient = ingredient;
//    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
