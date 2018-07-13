package com.example.irishka.jsonplaceholderproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PostModel> postModelList);

    @Query("SELECT * FROM PostModel")
    Single<List<PostModel>> getAllPosts();

}
