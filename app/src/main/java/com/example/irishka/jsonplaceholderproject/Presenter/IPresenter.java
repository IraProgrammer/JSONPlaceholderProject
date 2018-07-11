package com.example.irishka.jsonplaceholderproject.Presenter;

//TODO ты сощдаешь презентер не через интерфейс, да и покрывать его интерфейсом нет смысла
public interface IPresenter {
    void onDownloadPosts();
    void onStop();
}
