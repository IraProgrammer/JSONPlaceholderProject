package com.example.irishka.jsonplaceholderproject.model;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.irishka.jsonplaceholderproject.database.AppDatabase;

public class AppDatabaseManager {

    private static AppDatabaseManager databaseManager;

    private AppDatabase appDatabase;

    private AppDatabaseManager(){

    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }

    public static AppDatabaseManager getInstance(){

        if (databaseManager == null) {
            databaseManager = new AppDatabaseManager();
        }

        return databaseManager;

    }

    public void init(Context context){

        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "app_database").build();

    }

}
