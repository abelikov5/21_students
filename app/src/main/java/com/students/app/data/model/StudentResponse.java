package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("image_url")
    @Expose
    private String image;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("cursus_users")
    @Expose
    List<CursusResponse> cursus;

    @SerializedName("projects_users")
    @Expose
    List<ProjectsResponse> projects;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("achievements")
    @Expose
    private List<AchievementsResponse> achievements;

    @SerializedName("campus")
    @Expose
    private List<CampusResponse> campus;

    public List<CampusResponse> getCampus() {
        return campus;
    }

    public List<AchievementsResponse> getAchievements() {
        return achievements;
    }

    public List<ProjectsResponse> getProjects() {
        return projects;
    }

    public String getEmail() {
        return email;
    }

    public List<CursusResponse> getCursus() {
        return cursus;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage() {
        return image;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }
}
