package com.example.irishka.jsonplaceholderproject.presenter.presenterPost;

import com.example.irishka.jsonplaceholderproject.view.viewPost.IViewMain;
import com.example.irishka.jsonplaceholderproject.model.ApiManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class PostsListPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private IViewMain view;

    private Disposable disposable = Disposables.empty();

    public PostsListPresenter(IViewMain view) {
        this.view = view;
    }

    public void onDownloadPosts() {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = apiModel.getTypicodeApi()
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {view.showList(list);}, Throwable::printStackTrace);
    }

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
