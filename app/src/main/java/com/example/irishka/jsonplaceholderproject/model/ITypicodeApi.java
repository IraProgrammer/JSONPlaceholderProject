package com.example.irishka.jsonplaceholderproject.model;

import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITypicodeApi {

    @GET("/posts")
    Observable<List<PostModel>> getPosts();

    @GET("/posts/{number}/comments")
    Observable<List<CommentModel>> getCommentsPath(@Path("number") int number);

    @GET("/comments")
    Observable<List<CommentModel>> getCommentsQuery(@Query("number") int number);

}
