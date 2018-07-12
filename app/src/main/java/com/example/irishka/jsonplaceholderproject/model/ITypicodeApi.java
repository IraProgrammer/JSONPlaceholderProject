package com.example.irishka.jsonplaceholderproject.model;

import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITypicodeApi {

    @GET("/posts")
    Single<List<PostModel>> getPosts();

    @GET("/posts/{number}/comments")
    Single<List<CommentModel>> getCommentsPath(@Path("number") int number);

    @GET("/comments")
    Single<List<CommentModel>> getCommentsQuery(@Query("number") int number);

}
