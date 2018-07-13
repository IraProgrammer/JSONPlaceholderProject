package com.example.irishka.jsonplaceholderproject.presenter.presenterPost;

import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.model.AppDatabaseManager;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.view.viewPost.IViewMain;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsListPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private AppDatabaseManager databaseManager;

    private IViewMain view;

    private Disposable disposable;

    public PostsListPresenter(IViewMain view) {
        this.view = view;
        view.initDatabase();
        databaseManager = AppDatabaseManager.getInstance();
    }

    private void onSavePosts(List<PostModel> posts) {
        databaseManager.getAppDatabase().getPostDao().insertAll(posts);
    }

    private Single<List<PostModel>> getPostsFromInternet(){
        return apiModel.getTypicodeApi().getPosts()
                .doOnSuccess(this::onSavePosts);
    }

    private Single<List<PostModel>> getPostsFromDatabase(){
        return databaseManager.getAppDatabase().getPostDao().getAllPosts()
                .subscribeOn(Schedulers.io());
    }

    public void onDownloadPosts() {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = getPostsFromInternet()
                .onErrorResumeNext(getPostsFromDatabase())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(list -> view.showProgress())
                .doOnSuccess(list -> view.hideProgress())
                .doOnError(list -> view.hideProgress())
                .subscribe(list -> view.showList(list), Throwable::printStackTrace);
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
