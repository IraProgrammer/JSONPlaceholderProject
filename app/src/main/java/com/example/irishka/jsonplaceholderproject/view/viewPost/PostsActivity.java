package com.example.irishka.jsonplaceholderproject.view.viewPost;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.irishka.jsonplaceholderproject.R;
import com.example.irishka.jsonplaceholderproject.model.AppDatabaseManager;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.presenter.presenterPost.PostsListPresenter;
import com.example.irishka.jsonplaceholderproject.view.viewComment.CommentsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.BODY;
import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.ID;
import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.TITLE;

public class PostsActivity extends MvpAppCompatActivity implements IViewMain, PostsAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar_posts)
    ProgressBar progressBar;

    private PostsAdapter adapter;

    @InjectPresenter
    PostsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PostsAdapter(this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void showList(List<PostModel> postModelList) {
        adapter.setPostList(postModelList);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void initDatabase() {
         AppDatabaseManager.getInstance().init(this);
    }

    @Override
    public void onItemClick(PostModel postModel) {
        Intent intent = new Intent(PostsActivity.this, CommentsActivity.class);
        intent.putExtra(ID, postModel.getId());
        intent.putExtra(TITLE, postModel.getTitle());
        intent.putExtra(BODY, postModel.getBody());
        startActivity(intent);
    }
}