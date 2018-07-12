package com.example.irishka.jsonplaceholderproject.presenter.presenterPost;

import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.view.viewPost.IViewMain;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class PostsListPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private IViewMain view;

    private Disposable disposable;

    public PostsListPresenter(IViewMain view) {
        this.view = view;
    }

    public void onDownloadPosts() {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = apiModel.getTypicodeApi()
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(list -> view.setProgressBarVisible())
                .doOnSuccess(list -> view.setProgressBarGone())
                .doOnError(list -> view.setProgressBarGone())
                .subscribe(list -> view.showList(list), Throwable::printStackTrace);
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
