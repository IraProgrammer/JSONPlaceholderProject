package com.example.irishka.jsonplaceholderproject.view.viewComment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.irishka.jsonplaceholderproject.R;
import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.presenter.presenterComment.CommentsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.BODY;
import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.ID;
import static com.example.irishka.jsonplaceholderproject.view.viewPost.PostsAdapter.TITLE;

public class CommentsActivity extends MvpAppCompatActivity implements IViewComments {

    @BindView(R.id.tv_title_comm)
    TextView tvTitle;

    @BindView(R.id.tv_body_comm)
    TextView tvBody;

    @BindView(R.id.comments_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar_comments)
    ProgressBar progressBar;

    private CommentsAdapter adapter;

    @InjectPresenter
    CommentsPresenter presenter;

    @ProvidePresenter
    CommentsPresenter provideCommentsPresenter() {

        return new CommentsPresenter(getIntent().getIntExtra(ID, 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommentsAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showComments(List<CommentModel> commentModelList) {
        adapter.setPostList(commentModelList);
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
    public void showPost() {
        tvTitle.setText(getIntent().getStringExtra(TITLE));
        tvBody.setText(getIntent().getStringExtra(BODY));
    }
}