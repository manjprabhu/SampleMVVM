package com.example.samplemvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Expose
    @SerializedName("reputation")
    private String reputation;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("user_type")
    private String userType;

    @Expose
    @SerializedName("profile_image")
    private String profileImage;

    @Expose
    @SerializedName("display_name")
    private String displayName;

    @Expose
    @SerializedName("link")
    private String link;
}
