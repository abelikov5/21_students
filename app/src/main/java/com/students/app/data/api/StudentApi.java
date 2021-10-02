package com.students.app.data.api;

import com.students.app.data.model.StudentResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentApi {
    @GET("v2/users/{login}")
    public Call<StudentResponse> getStudent(@Path("login") String login);
}
