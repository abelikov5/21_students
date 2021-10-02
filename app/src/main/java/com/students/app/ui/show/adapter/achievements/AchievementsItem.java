package com.students.app.ui.show.adapter.achievements;

public class AchievementsItem {

    private final String description;
    private final String image;

    public AchievementsItem(String description, String image) {
        this.description = description;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
