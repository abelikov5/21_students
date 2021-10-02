package com.students.app.ui.show;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.students.app.R;
import com.students.app.data.api.StudentService;
import com.students.app.data.model.AchievementsResponse;
import com.students.app.data.model.CursusResponse;
import com.students.app.data.model.ProjectsResponse;
import com.students.app.data.model.StudentResponse;
import com.students.app.ui.show.adapter.achievements.AchievementsAdapter;
import com.students.app.ui.show.adapter.achievements.AchievementsItem;
import com.students.app.ui.show.adapter.common.CommonAdapter;
import com.students.app.ui.show.adapter.common.CommonItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity {
    ImageView showImage;
    TextView textLogin, textLastName, textNoInfo;
    TextView textName, textEmail, textLevel, campusText;
    RelativeLayout skillsRL, projectsRL, achievementsRL, campusRL;
    View divider;
    ProgressBar progress;

    RecyclerView skillsRecycler;
    RecyclerView projectsRecycler;
    RecyclerView achievementsRecycler;
    CommonAdapter commonAdapter;
    AchievementsAdapter achievementsAdapter;

    int count1, count2, count3, count4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        skillsRecycler = findViewById(R.id.showSkillsRecycler);

        campusText = findViewById(R.id.showCampusText);

        skillsRL = findViewById(R.id.showSkillsRL);
        projectsRL = findViewById(R.id.showProjectsRL);
        achievementsRL = findViewById(R.id.showAchievementsRL);
        campusRL = findViewById(R.id.showCampusRL);

        achievementsRecycler = findViewById(R.id.showAchievementsRecycler);

        textNoInfo = findViewById(R.id.showTextNoInfo);
        projectsRecycler = findViewById(R.id.showProjectsRecycler);
        textLevel = findViewById(R.id.showTextLevel);
        divider = findViewById(R.id.showDivider);
        textLogin = findViewById(R.id.showTextLogin);
        textLastName = findViewById(R.id.showTextLastName);
        textName = findViewById(R.id.showTextName);
        textEmail = findViewById(R.id.showTextEmail);

        showImage = findViewById(R.id.showImage);

        progress = findViewById(R.id.showProgress);


        NestedScrollView nestedScrollView = findViewById(R.id.showNestedScroll);

        AtomicInteger scrollY = new AtomicInteger();

        findViewById(R.id.showSkillsOpenImage).setOnClickListener(v -> {
            scrollY.set(nestedScrollView.getScrollY());
            if (count1 == 1){
                count1 = 0;
                skillsRecycler.animate()
                        .translationY(0)
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                skillsRecycler.setVisibility(View.GONE);
                            }
                        })
                        ;
            } else {
                count1 = 1;
                skillsRecycler.setVisibility(View.VISIBLE);
                skillsRecycler.setAlpha(0.0f);
                nestedScrollView.scrollTo(0, scrollY.get());

                skillsRecycler.animate()
                        .translationY(1.0f)
                        .alpha(1.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                skillsRecycler.setVisibility(View.VISIBLE);
                                nestedScrollView.scrollTo(0, scrollY.get());
                            }
                        })
                        ;

            }
        });

        findViewById(R.id.showProjectsOpenImage).setOnClickListener(v -> {
            scrollY.set(nestedScrollView.getScrollY());
            if (count2 == 1){
                count2 = 0;
                projectsRecycler.animate()
                        .translationY(0)
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                projectsRecycler.setVisibility(View.GONE);
                            }
                        })
                        ;
            } else {
                count2 = 1;
                projectsRecycler.animate()
                        .translationY(1.0f)
                        .alpha(1.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                projectsRecycler.setVisibility(View.VISIBLE);
                                nestedScrollView.scrollTo(0, scrollY.get());
                            }
                        })
                ;

            }
        });

        findViewById(R.id.showAchievementsOpenImage).setOnClickListener(v -> {

            scrollY.set(nestedScrollView.getScrollY());
            if (count3 == 1){
                count3 = 0;

                achievementsRecycler.animate()
                        .translationY(0)
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                achievementsRecycler.setVisibility(View.GONE);
                            }
                        })
                        ;
            } else {
                count3 = 1;
                achievementsRecycler.setVisibility(View.VISIBLE);
                achievementsRecycler.setAlpha(0.0f);
                achievementsRecycler.animate()
                        .translationY(1.0f)
                        .alpha(1.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                achievementsRecycler.setVisibility(View.VISIBLE);
                                nestedScrollView.scrollTo(0, scrollY.get());
                            }
                        })
                        ;

            }
        });

        findViewById(R.id.showCampusOpenImage).setOnClickListener(v ->{
            scrollY.set(nestedScrollView.getScrollY());
            if (count4 == 1){
                count4 = 0;
                campusText.animate()
                        .translationY(0)
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                campusText.setVisibility(View.GONE);
                            }
                        })
                        ;
            } else {
                count4 = 1;
                campusText.animate()
                        .translationY(1.0f)
                        .alpha(1.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                campusText.setVisibility(View.VISIBLE);
                                nestedScrollView.post(() -> nestedScrollView.fullScroll(View.FOCUS_DOWN));
                            }
                        })
                        ;

            }
        });


        Intent intent = getIntent();
        String login = intent.getStringExtra("login");

        getStudent(login);
    }

    private void hideUi(boolean hide) {
        if (hide){
            showImage.setVisibility(View.GONE);
            textLogin.setVisibility(View.GONE);
            textName.setVisibility(View.GONE);
            textLastName.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            textEmail.setVisibility(View.GONE);
            textLevel.setVisibility(View.GONE);
            skillsRL.setVisibility(View.GONE);
            projectsRL.setVisibility(View.GONE);
            achievementsRL.setVisibility(View.GONE);
            campusRL.setVisibility(View.GONE);
        } else {
            showImage.setVisibility(View.VISIBLE);
            textLogin.setVisibility(View.VISIBLE);
            textName.setVisibility(View.VISIBLE);
            textLastName.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            textEmail.setVisibility(View.VISIBLE);
            textLevel.setVisibility(View.VISIBLE);
            skillsRL.setVisibility(View.VISIBLE);
            projectsRL.setVisibility(View.VISIBLE);
            achievementsRL.setVisibility(View.VISIBLE);
            campusRL.setVisibility(View.VISIBLE);
        }

    }
    private void setupText(StudentResponse studentResponse){
        textLogin.setText(getResources().getString(R.string.user_login) + ' ' + studentResponse.getLogin());
        textName.setText(getResources().getString(R.string.user_name) + ' ' + studentResponse.getFirstName());
        textLastName.setText(getResources().getString(R.string.user_lastname) + ' ' + studentResponse.getLastName());
        textEmail.setText("Email: \n" + studentResponse.getEmail());

        campusText.setText(
                "Имя: " + studentResponse.getCampus().get(0).getName() + "\n" +
                "Страна: " + studentResponse.getCampus().get(0).getCountry() + "\n" +
                "Адрес: " + studentResponse.getCampus().get(0).getAddress() + "\n" +
                "Website: " + studentResponse.getCampus().get(0).getWebsite() + "\n" +
                "Facebook: " + studentResponse.getCampus().get(0).getFacebook()
        );

        setupSkillsRecycler(studentResponse.getCursus());
        setupProjectsRecycler(studentResponse.getProjects());
        setupAchievementsRecycler(studentResponse.getAchievements());
    }

    private void setupSkillsRecycler(List<CursusResponse> cursus){
        ArrayList<CommonItem> list = new ArrayList<>();
        for (int i = 0; i < cursus.size(); i++) {
            if (cursus.get(i).getGrade() != null) {
                if (cursus.get(i).getGrade().equals("Member")) {
                    for (int j = 0; j < cursus.get(i).getSkills().size(); j++) {
                        list.add(new CommonItem(
                                cursus.get(i).getSkills().get(j).getName() +
                                        " - level " + cursus.get(i).getSkills().get(j).getLevel()
                        ));
                    }
                    textLevel.setText("Level: " + cursus.get(i).getLevel());
                    break;
                }
            }
        }
        skillsRecycler.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
        skillsRecycler.addItemDecoration(new DividerItemDecoration(ShowActivity.this,
                DividerItemDecoration.VERTICAL));
        commonAdapter = new CommonAdapter(ShowActivity.this, list);
        skillsRecycler.setAdapter(commonAdapter);
    }

    private void setupProjectsRecycler(List<ProjectsResponse> projects){
        ArrayList<CommonItem> list = new ArrayList<>();

        for (int i = 0; i < projects.size(); i++) {
            list.add(new CommonItem(
                    "Имя: " + projects.get(i).getProject().getName() +
                            "\n\nСтатус: " + projects.get(i).getStatus() +
                            "\n\nДата: " + convertDate(projects.get(i).getCreated()) +
                            "\n\nОценка: " + projects.get(i).getMark()
            ));
        }

        projectsRecycler.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
        projectsRecycler.addItemDecoration(new DividerItemDecoration(ShowActivity.this,
                DividerItemDecoration.VERTICAL));
        commonAdapter = new CommonAdapter(ShowActivity.this, list);
        projectsRecycler.setAdapter(commonAdapter);
    }

    private void setupAchievementsRecycler(List<AchievementsResponse> achievements){
        ArrayList<AchievementsItem> list = new ArrayList<>();

        for (int i = 0; i < achievements.size(); i++){
            list.add(
                    new AchievementsItem(
                            achievements.get(i).getDescription(),
                            "https://api.intra.42.fr/" + achievements.get(i).getImage()
                    )
            );
        }

        achievementsRecycler.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
        achievementsRecycler.addItemDecoration(new DividerItemDecoration(ShowActivity.this,
                DividerItemDecoration.VERTICAL));
        achievementsAdapter = new AchievementsAdapter(ShowActivity.this, list);
        achievementsRecycler.setAdapter(achievementsAdapter);
    }

    private String convertDate(String date){
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = null;
        try {
            convertedDate = sourceFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return destFormat.format(convertedDate);
    }

    private void getStudent(String login){
        StudentService
                .getInstance(ShowActivity.this)
                .getStudentApi()
                .getStudent(login)
                .enqueue(new Callback<StudentResponse>() {
                    @Override
                    public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                        Log.d("code", String.valueOf(response.code()));
                        if (response.code()==200){
                            StudentResponse studentResponse = response.body();

                            Glide
                                    .with(ShowActivity.this)
                                    .load(studentResponse.getImage())
                                    .circleCrop()
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            hideUi(false);
                                            progress.setVisibility(View.GONE);
                                            setupText(studentResponse);
                                            return false;
                                        }
                                    })
                                    .into(showImage);
                            Log.d("id", String.valueOf(studentResponse.getId()));
                        }
                        if (response.code() == 404){
                            Log.d("404", String.valueOf(response.code()));

                            textNoInfo.setVisibility(View.VISIBLE);
                            progress.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<StudentResponse> call, Throwable t) {
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        if (activeNetwork == null) {
                            runOnUiThread(() -> Toast.makeText(
                                    ShowActivity.this,
                                    "Нет подключения к интернету!",
                                    Toast.LENGTH_LONG
                            ).show());
                        } else {
                            runOnUiThread(() -> Toast.makeText(
                                    ShowActivity.this,
                                    "Произошла ошибка!",
                                    Toast.LENGTH_LONG
                            ).show());
                        }
                    }
                });
    }

}