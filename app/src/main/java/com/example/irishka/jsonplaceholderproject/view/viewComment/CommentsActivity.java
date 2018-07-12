package com.example.irishka.jsonplaceholderproject.view.viewComment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.irishka.jsonplaceholderproject.R;
import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.presenter.presenterComment.CommentsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.irishka.jsonplaceholderproject.PostsAdapter.BODY;
import static com.example.irishka.jsonplaceholderproject.PostsAdapter.ID;
import static com.example.irishka.jsonplaceholderproject.PostsAdapter.TITLE;

public class CommentsActivity extends AppCompatActivity implements IViewComments {

    @BindView(R.id.tv_title_comm)
    TextView tvTitle;

    @BindView(R.id.tv_body_comm)
    TextView tvBody;

    @BindView(R.id.comments_recycler_view)
    RecyclerView recyclerView;

    private CommentsAdapter adapter;

    private CommentsPresenter presenter;

    // TODO: не нужно создавать ссылку на интент
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        intent = getIntent();

        presenter = new CommentsPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommentsAdapter();
        recyclerView.setAdapter(adapter);

        presenter.onDownloadComments(intent.getIntExtra(ID, 1));
    }

    @Override
    public void showComments(List<CommentModel> commentModelList) {

        // TODO: эти данные из интента не нужно доставить, когда придут комменты
        // а вдруг интернет будет слабый? в таком случае пост не отобразится
        // так же предлагаю сделать прогресс бар пока грузятся данные
        tvTitle.setText(intent.getStringExtra(TITLE));
        tvBody.setText(intent.getStringExtra(BODY));

        adapter.setPostList(commentModelList);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }
}
