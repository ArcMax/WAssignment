package com.archana.WAssignment.model;

import java.io.Serializable;

public class BadgeCount implements Serializable {
    private int bronze;
    private int silver;
    private int gold;

    public BadgeCount(int bronze, int silver, int gold){
        this.bronze = bronze;
        this.silver = silver;
        this.gold = gold;
    }

    public int getBronze() {
        return bronze;
    }

    public int getSilver() {
        return silver;
    }

    public int getGold() {
        return gold;
    }
}
