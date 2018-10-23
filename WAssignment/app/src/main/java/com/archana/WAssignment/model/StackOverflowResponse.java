package com.archana.WAssignment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StackOverflowResponse {
    @SerializedName("items")
    private List<StackOverflowItems> itemListElement;
    @SerializedName("has_more")
    private boolean has_more;
    @SerializedName("quota_max")
    private int quota_max;
    @SerializedName("quota_remaining")
    private int quota_remaining;

    public StackOverflowResponse(List<StackOverflowItems> itemListElement, boolean has_more, int quota_max, int quota_remaining){
        this.itemListElement = itemListElement;
        this.has_more = has_more;
        this.quota_max = quota_max;
        this.quota_remaining = quota_remaining;
    }

    public List<StackOverflowItems> getResults() {
        return itemListElement;
    }

    public List<StackOverflowItems> getItemListElement() {
        return itemListElement;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }
}

