package com.example.irishka.jsonplaceholderproject.model.modelComment;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(
        entity = PostModel.class,
        parentColumns = "id",
        childColumns = "postId"))

public class CommentModel {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int idComm;

    @SerializedName("postId")
    @Expose
    private int postId;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("body")
    @Expose
    private String body;

    public int getIdComm() {
        return idComm;
    }

    public void setIdComm(int idComm) {
        this.idComm = idComm;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
