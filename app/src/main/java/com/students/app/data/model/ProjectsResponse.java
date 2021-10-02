package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectsResponse {


    public ProjectsResponse(Project project, int mark, String status, String created) {
        this.project = project;
        this.mark = mark;
        this.status = status;
        this.created = created;
    }

    @SerializedName("project")
    @Expose
    private Project project;

    public Project getProject() {
        return project;
    }

    @SerializedName("final_mark")
    @Expose
    private int mark;

    public int getMark() {
        return mark;
    }

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    @SerializedName("created_at")
    @Expose
    private String created;

    public String getCreated() {
        return created;
    }

    public static class Project{

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }
    }
}

