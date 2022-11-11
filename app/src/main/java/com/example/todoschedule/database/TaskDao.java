package com.example.todoschedule.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertAll(Task tasks);

    @Update
    public Completable update(Task tasks);

    @Delete
    public Completable delete(Task tasks);

    //全部取り出す
    @Query("SELECT * FROM tasks")
    public Flowable<List<Task>> getAllTasks();

    //あとで取り出すやつ追加
}
