package com.example.samplemvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @Expose
    @SerializedName("owner")
    private Owner owner;

    @Expose
    @SerializedName("is_accepted")
    private boolean isAccepted;

    @Expose
    @SerializedName("score")
    private Integer score;

    @Expose
    @SerializedName("last_activity_date")
    private Integer lastActivityDate;

    @Expose
    @SerializedName("creation_date")
    private Integer creationDate;

    @Expose
    @SerializedName("answer_id")
    private Integer answerId;

    @Expose
    @SerializedName("question_id")
    private Integer questionId;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Integer lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }





}
