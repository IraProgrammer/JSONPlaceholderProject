package com.example.irishka.jsonplaceholderproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;

@Database(entities = {PostModel.class, CommentModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PostDao getPostDao();
    public abstract CommentDao getCommentDao();
}
