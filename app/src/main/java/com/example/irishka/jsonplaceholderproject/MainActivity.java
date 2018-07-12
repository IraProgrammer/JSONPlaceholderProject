package com.example.irishka.jsonplaceholderproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.presenter.presenterPost.PostsListPresenter;
import com.example.irishka.jsonplaceholderproject.view.viewPost.IViewMain;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO: MainActivity и PostsAdapter тоже относятся к view постов
// стоит закинуть в папку, где лежить интрефейс IViewMain
public class MainActivity extends AppCompatActivity implements IViewMain {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PostsAdapter adapter;

    private PostsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new PostsListPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PostsAdapter(this);
        recyclerView.setAdapter(adapter);
        presenter.onDownloadPosts();
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
}