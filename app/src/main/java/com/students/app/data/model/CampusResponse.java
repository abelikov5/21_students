package com.students.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CampusResponse {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("country")
    @Expose
    private String country;

    public String getCountry() {
        return country;
    }

    @SerializedName("address")
    @Expose
    private String address;

    public String getAddress() {
        return address;
    }

    @SerializedName("website")
    @Expose
    private String website;

    public String getWebsite() {
        return website;
    }

    @SerializedName("facebook")
    @Expose
    private String facebook;

    public String getFacebook() {
        return facebook;
    }
}
