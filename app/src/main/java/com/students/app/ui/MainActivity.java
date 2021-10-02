package com.students.app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.students.app.R;
import com.students.app.data.api.TokenService;
import com.students.app.data.model.TokenResponse;
import com.students.app.ui.show.ShowActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getToken();

        Button buttonSearch = findViewById(R.id.mainButtonSearch);
        EditText editText = findViewById(R.id.mainEditText);
        Glide
                .with(this)
                .load(R.drawable.logo_sreen)
                .into((ImageView) findViewById(R.id.mainImageView));

        buttonSearch.setOnClickListener(v -> {
            if (editText.getText().toString().trim().equals("")){
                Toast.makeText(MainActivity.this, "Введите значение!", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("login", editText.getText().toString().trim().toLowerCase());
                startActivity(intent);
            }
        });

    }

    private void getToken(){
        TokenService
                .getInstance()
                .getTokenApi()
                .getToken(
                        getString(R.string.grant_type),
                        getString(R.string.client_id),
                        getString(R.string.client_secret)
                )
                .enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        TokenResponse tokenResponse = response.body();
                        if (tokenResponse != null) {
                            SharedPreferences prefs = getSharedPreferences("SP", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString("token", tokenResponse.getAccessToken());
                            editor.apply();

                        }
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        if (activeNetwork == null) {
                            runOnUiThread(() -> Toast.makeText(
                                    MainActivity.this,
                                    R.string.no_token_internet,
                                    Toast.LENGTH_LONG
                            ).show());
                        } else {
                            runOnUiThread(() -> Toast.makeText(
                                    MainActivity.this,
                                    R.string.no_token,
                                    Toast.LENGTH_LONG
                            ).show());
                        }
                    }
                });
    }
}