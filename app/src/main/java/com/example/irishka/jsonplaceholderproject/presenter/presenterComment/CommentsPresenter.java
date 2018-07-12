package com.example.irishka.jsonplaceholderproject.presenter.presenterComment;

import com.example.irishka.jsonplaceholderproject.model.ApiManager;
import com.example.irishka.jsonplaceholderproject.view.viewComment.IViewComments;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class CommentsPresenter {

    private ApiManager apiModel = ApiManager.getInstance();

    private IViewComments view;

    // TODO: видимо, при мердже потерялось
    //TODO лучше сделать lazy
    // достаточно убрать инициализацию отсюда и перед обращениями к disposable добавить проверку на null
    private Disposable disposable = Disposables.empty();


    public CommentsPresenter(IViewComments view) {
        this.view = view;
    }

    // TODO: в плане нейминга скорее не number, а postId
    public void onDownloadComments(int number) {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = apiModel.getTypicodeApi()
                .getCommentsPath(number)
                .observeOn(AndroidSchedulers.mainThread())
                // TODO: если лямбда в одну строку, то фигурные скобки не нужны
                .subscribe(list -> {view.showComments(list);}, Throwable::printStackTrace);
    }

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
