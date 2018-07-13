package com.example.irishka.jsonplaceholderproject.presenter.presenterPost;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.model.AppDatabaseManager;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.view.viewPost.IViewMain;
import com.example.irishka.jsonplaceholderproject.view.viewPost.PostsActivity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PostsListPresenter extends MvpPresenter<IViewMain>{

    private ApiManager apiModel = ApiManager.getInstance();

    private AppDatabaseManager databaseManager;

    private Disposable disposable;

    public PostsListPresenter() {
        databaseManager = AppDatabaseManager.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().initDatabase();
        onDownloadPosts();
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

    private void onDownloadPosts() {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = getPostsFromInternet()
                .onErrorResumeNext(getPostsFromDatabase())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(list -> getViewState().showProgress())
                .doOnSuccess(list -> getViewState().hideProgress())
                .doOnError(list -> getViewState().hideProgress())
                .subscribe(list -> getViewState().showList(list), Throwable::printStackTrace);
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
