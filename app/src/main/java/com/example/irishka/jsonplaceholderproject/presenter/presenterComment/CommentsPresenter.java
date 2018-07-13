package com.example.irishka.jsonplaceholderproject.presenter.presenterComment;

import com.example.irishka.jsonplaceholderproject.database.CommentDao;
import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.model.AppDatabaseManager;
import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.view.viewComment.IViewComments;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

public class CommentsPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private AppDatabaseManager databaseManager;

    private IViewComments view;

    private Disposable disposable;

    public CommentsPresenter(IViewComments view) {
        this.view = view;
        databaseManager = AppDatabaseManager.getInstance();
    }

    private void onSaveComments(List<CommentModel> comments) {
        databaseManager.getAppDatabase().getCommentDao().insertAll(comments);
    }

    private Single<List<CommentModel>> getCommentsFromInternet(int postId){
        return apiModel.getTypicodeApi().getCommentsPath(postId)
                .doOnSuccess(this::onSaveComments);
    }

    private Single<List<CommentModel>> getCommentsFromDatabase(int postId){
        return databaseManager.getAppDatabase().getCommentDao().getAllComments(postId)
                .subscribeOn(Schedulers.io());
    }

    public void onDownloadComments(int postId) {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = getCommentsFromInternet(postId)
                .onErrorResumeNext(getCommentsFromDatabase(postId))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(list -> view.showProgress())
                .doOnSuccess(list -> view.hideProgress())
                .doOnError(list -> view.hideProgress())
                .subscribe(list -> view.showComments(list), Throwable::printStackTrace);
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
