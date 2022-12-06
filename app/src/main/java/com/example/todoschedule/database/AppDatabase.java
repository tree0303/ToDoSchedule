package com.example.todoschedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "tasks";

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            synchronized (AppDatabase.class){
                instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
            }
        }
        return instance;
    }
}
