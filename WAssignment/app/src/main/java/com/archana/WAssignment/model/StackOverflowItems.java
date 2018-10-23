package com.archana.WAssignment.model;

import java.io.Serializable;

public class StackOverflowItems implements Serializable {
    private String display_name;
    private String profile_image;
    private BadgeCount badge_counts;


    public StackOverflowItems(String display_name, String profile_image, BadgeCount badge_counts) {
        this.display_name = display_name;
        this.profile_image = profile_image;
        this.badge_counts = badge_counts;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public BadgeCount getBadge_counts() {
        return badge_counts;
    }
}
