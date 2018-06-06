package com.example.lukaszwachowski.bakingapp.data.database;

import android.arch.persistence.room.TypeConverter;

import com.example.lukaszwachowski.bakingapp.network.model.Ingredient;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public static List<Ingredient> stringToIngredient(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ingredient>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ingredientToString(List<Ingredient> ingredients) {
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }

    @TypeConverter
    public static List<Step> stringToStep(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Step>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String stepsToString(List<Step> steps) {
        Gson gson = new Gson();
        return gson.toJson(steps);
    }
}
