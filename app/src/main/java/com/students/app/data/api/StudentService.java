package com.students.app.data.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentService {
    private static StudentService instance;
    public static final String BASE_URL = "https://api.intra.42.fr/";
    private static Retrofit retrofit;

    private StudentService(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        SharedPreferences prefs = context.getSharedPreferences("SP", Context.MODE_PRIVATE);

        String token = prefs.getString("token", "");
        Log.d("token", token);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                            Request request = chain.request();

                            request = request.newBuilder()
                                    .addHeader("Authorization", "Bearer " + token)
                                    .build();
                            Log.d("request", request.toString());

                            return chain.proceed(request);
                        }
                )
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static StudentService getInstance(Context context) {
        if (instance == null) {
            instance = new StudentService(context);
        }
        return instance;
    }
    public StudentApi getStudentApi() {
        return retrofit.create(StudentApi.class);
    }
}
