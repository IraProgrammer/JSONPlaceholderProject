package com.example.irishka.jsonplaceholderproject.view.viewComment;

import com.arellomobile.mvp.MvpView;
import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;

import java.util.List;

public interface IViewComments extends MvpView{

    void showComments(List<CommentModel> commentModelList);

    void showProgress();

    void hideProgress();

    void showPost();

}
