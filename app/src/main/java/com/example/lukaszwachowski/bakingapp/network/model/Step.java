package com.example.lukaszwachowski.bakingapp.network.model;

import com.google.gson.annotations.SerializedName;

//@Entity(tableName = "steps")
public class Step {

    public int recipeId;

    //    @PrimaryKey
    @SerializedName("id")
    public int id;

    @SerializedName("shortDescription")
    public String shortDescription;

    @SerializedName("description")
    public String description;

    @SerializedName("videoURL")
    public String videoURL;

    @SerializedName("thumbnailURL")
    public String thumbnailURL;

//    public Step(int recipeId, int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
//        this.recipeId = recipeId;
//        this.id = id;
//        this.shortDescription = shortDescription;
//        this.description = description;
//        this.videoURL = videoURL;
//        this.thumbnailURL = thumbnailURL;
//    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
