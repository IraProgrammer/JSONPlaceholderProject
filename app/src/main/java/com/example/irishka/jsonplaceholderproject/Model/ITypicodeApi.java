package com.example.irishka.jsonplaceholderproject.Model;

import com.example.irishka.jsonplaceholderproject.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ITypicodeApi {

    @GET("/posts")
    Observable<List<PostModel>> getPosts();
}
