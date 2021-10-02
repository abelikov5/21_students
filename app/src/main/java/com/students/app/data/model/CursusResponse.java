package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CursusResponse {

    @SerializedName("skills")
    @Expose
    List<SkillsResponse> skills;

    public List<SkillsResponse> getSkills() {
        return skills;
    }

    @SerializedName("level")
    @Expose
    private Double level;

    public Double getLevel() {
        return level;
    }

    @SerializedName("grade")
    @Expose
    private String grade;

    public String getGrade() {
        return grade;
    }
}
