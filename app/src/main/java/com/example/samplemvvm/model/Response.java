package com.example.samplemvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @Expose
    @SerializedName("items")
    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(String quotaMax) {
        this.quotaMax = quotaMax;
    }

    public String getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(String quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    @Expose
    @SerializedName("has_more")
    private boolean hasMore;

    @Expose
   @SerializedName("quota_max")
   private String quotaMax;


    @Expose
   @SerializedName("quota_remaining")
    private String quotaRemaining;

}
