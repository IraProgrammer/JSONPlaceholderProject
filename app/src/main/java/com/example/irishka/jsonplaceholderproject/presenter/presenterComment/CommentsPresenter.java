package com.example.irishka.jsonplaceholderproject.presenter.presenterComment;

import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.view.viewComment.IViewComments;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class CommentsPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private IViewComments view;

    private Disposable disposable = Disposables.empty();


    public CommentsPresenter(IViewComments view) {
        this.view = view;
    }

    public void onDownloadComments(int number) {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = apiModel.getTypicodeApi()
                .getCommentsPath(number)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {view.showComments(list);}, Throwable::printStackTrace);
    }

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
