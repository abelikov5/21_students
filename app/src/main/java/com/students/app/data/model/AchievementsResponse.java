package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AchievementsResponse {

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }


    @SerializedName("image")
    @Expose
    private String image;

    public String getImage() {
        return image;
    }
}
