package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillsResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("level")
    @Expose
    private Double level;

    public Double getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }


}
