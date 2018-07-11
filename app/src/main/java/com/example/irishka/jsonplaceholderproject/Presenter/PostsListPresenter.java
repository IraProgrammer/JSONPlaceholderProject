package com.example.irishka.jsonplaceholderproject.Presenter;

import com.example.irishka.jsonplaceholderproject.IView;
import com.example.irishka.jsonplaceholderproject.Model.ApiManager;
import com.example.irishka.jsonplaceholderproject.Model.ITypicodeApi;
import com.example.irishka.jsonplaceholderproject.Presenter.IPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class PostsListPresenter implements IPresenter {

    //TODO создавать новую ссылку нет смысла нет смысла,
    // лучше просто вызывать ApiManager.getInstance().getApi(), там где используется модель
    private ITypicodeApi typicodeModel = ApiManager.getInstance();

    private IView view;

    //TODO лучше сделать lazy
    // достаточно убрать инициализацию отсюда и перед обращениями к disposable добавить проверку на null
    private Disposable disposable = Disposables.empty();

    //TODO у презентера есть ссылка на view, тут может быть учтека
    // нужно на ее делать null, хотя бы когда активити уничтожается
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
                //TODO если лямбда в одну строку
                .subscribe(list -> {view.showList(list);}, Throwable::printStackTrace);
    }

    @Override
    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
