package com.example.irishka.jsonplaceholderproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//TODO папки именуются также как и переменные, camelCase и с маленькой буквы (не знал куда вставить этот туду)
public class PostModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
