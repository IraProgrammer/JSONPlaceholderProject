package com.example.irishka.jsonplaceholderproject.presenter.presenterComment;

import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.view.viewComment.IViewComments;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class CommentsPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private IViewComments view;

    private Disposable disposable;

    public CommentsPresenter(IViewComments view) {
        this.view = view;
    }

    public void onDownloadComments(int postId) {

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = apiModel.getTypicodeApi()
                .getCommentsPath(postId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(list -> view.setProgressBarVisible())
                .doOnSuccess(list -> view.setProgressBarGone())
                .doOnError(list -> view.setProgressBarGone())
                .subscribe(list -> view.showComments(list), Throwable::printStackTrace);
    }

    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
