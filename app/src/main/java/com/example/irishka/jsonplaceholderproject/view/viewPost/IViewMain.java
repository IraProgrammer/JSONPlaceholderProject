package com.example.irishka.jsonplaceholderproject.view.viewPost;

import android.content.Context;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

public interface IViewMain extends MvpView {

    void showList(List<PostModel> postModelList);

    void showProgress();

    void hideProgress();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void initDatabase();
}
