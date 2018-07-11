package com.example.irishka.jsonplaceholderproject.Presenter;

import com.example.irishka.jsonplaceholderproject.IView;
import com.example.irishka.jsonplaceholderproject.Model.ApiManager;
import com.example.irishka.jsonplaceholderproject.Model.ITypicodeApi;
import com.example.irishka.jsonplaceholderproject.Presenter.IPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class PostsListPresenter implements IPresenter {

    private ITypicodeApi typicodeModel = ApiManager.getInstance();

    private IView view;

    private Disposable disposable = Disposables.empty();

    public PostsListPresenter(IView view) {
        this.view = view;
    }

    @Override
    public void onDownloadPosts() {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = typicodeModel.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {view.showList(list);}, Throwable::printStackTrace);
    }

    @Override
    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
