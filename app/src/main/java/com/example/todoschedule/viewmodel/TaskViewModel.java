package com.example.todoschedule.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.todoschedule.database.AppDatabase;
import com.example.todoschedule.database.Task;
import com.example.todoschedule.database.TaskDao;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;

public class TaskViewModel extends AndroidViewModel{

    private TaskDao taskDao;
    private List<Task> taskList;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
    }

    public Flowable<List<Task>> getAllTaskList(){
        return taskDao.getAllTasks().map(tasks -> taskList = tasks);
    }

    public Completable insert(String taskname, String datetime, String returndegree, Integer returnnum){
        Task task = new Task();
        task.setTaskName(taskname);
        task.setDatetime(datetime);
        task.setReturndegree(returndegree);
        task.setReturnnum(returnnum);
        return taskDao.insertAll(task);
    }
    public Completable delete(Task task){
        return taskDao.delete(task);
    }



}
