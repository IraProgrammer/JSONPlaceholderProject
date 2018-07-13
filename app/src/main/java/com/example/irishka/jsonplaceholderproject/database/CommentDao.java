package com.example.irishka.jsonplaceholderproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentModel> commentModelList);

    @Query("SELECT * FROM CommentModel WHERE postId = :id")
    Single<List<CommentModel>> getAllComments(int id);

}

