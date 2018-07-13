package com.example.irishka.jsonplaceholderproject.view.viewPost;

import android.content.Context;

import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

public interface IViewMain {

    void showList(List<PostModel> postModelList);

    void showProgress();

    void hideProgress();

    void initDatabase();
}
