package com.example.todoschedule.viewmodel;

import android.app.Application;

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
import io.reactivex.rxjava3.core.Flowable;

public class TaskViewModel extends AndroidViewModel {

    private TaskDao taskDao;
    private List<Task> taskList;
    private static String nowDateTime = (DateTimeFormatter.ofPattern("yyyy/MM/dd")
            .format(LocalDateTime.now()));


    public TaskViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
    }


    public Boolean isThisDay(String date){
        StringBuilder lastdatetime = new StringBuilder();
        lastdatetime.append(nowDateTime);
        lastdatetime.append(" 23:59");
        StringBuilder startdatetime = new StringBuilder();
        startdatetime.append(nowDateTime);
        startdatetime.append(" 00:00");
        boolean flag = (date.compareTo(lastdatetime.toString()) <= 0
                && date.compareTo(startdatetime.toString()) >= 0);
        return flag;
    }
    public Boolean isThisWeek(String date){
        String nowweek = LocalDateTime.parse(nowDateTime).with(DayOfWeek.SUNDAY).toString();
        StringBuilder lastdatetime = new StringBuilder();
        lastdatetime.append(nowweek);
        lastdatetime.append(" 23:59");
        StringBuilder startdatetime = new StringBuilder();
        startdatetime.append(nowDateTime);
        startdatetime.append(" 00:00");
        boolean flag = (date.compareTo(lastdatetime.toString()) <= 0
                && date.compareTo(startdatetime.toString()) >= 0);
        return flag;
    }
    public Boolean isThisMonth(String date){
        String nowstartmonth = LocalDateTime.parse(nowDateTime).withDayOfMonth(1).toString();
        String nowlastmonth = LocalDateTime.parse(nowDateTime).withDayOfMonth(1).plusMonths(1).minusDays(1).toString();
        StringBuilder lastdatetime = new StringBuilder();
        lastdatetime.append(nowlastmonth);
        lastdatetime.append(" 23:59");
        StringBuilder startdatetime = new StringBuilder();
        startdatetime.append(nowstartmonth);
        startdatetime.append(" 00:00");
        boolean flag = (date.compareTo(lastdatetime.toString()) <= 0
                && date.compareTo(startdatetime.toString()) >= 0);
        return flag;
    }

    public Boolean isAfterThisMonth(String date){
        String nextstartmonth = LocalDateTime.parse(nowDateTime).withDayOfMonth(1).plusMonths(1).toString();
        StringBuilder startdatetime = new StringBuilder();
        startdatetime.append(nextstartmonth);
        startdatetime.append(" 00:00");
        boolean flag = (date.compareTo(startdatetime.toString()) >= 0);
        return flag;
    }

    public Flowable<List<Task>> getThisDayTaskList(){
        return taskDao.getAllTasks().map(tasks -> {
           taskList = tasks;
           return tasks.stream()
                   .filter(task ->  isThisDay(task.getDatetime()) == true)
                   .collect(Collectors.toList());
        });
    }

    public Flowable<List<Task>> getThisWeekTaskList(){
        return taskDao.getAllTasks().map(tasks -> {
            taskList = tasks;
            return tasks.stream()
                    .filter(task ->  isThisWeek(task.getDatetime()) == true)
                    .collect(Collectors.toList());
        });
    }
    public Flowable<List<Task>> getThisMonthTaskList(){
        return taskDao.getAllTasks().map(tasks -> {
            taskList = tasks;
            return tasks.stream()
                    .filter(task ->  isThisMonth(task.getDatetime()) == true)
                    .collect(Collectors.toList());
        });
    }
    public Flowable<List<Task>> getAfterThisMonthTaskList(){
        return taskDao.getAllTasks().map(tasks -> {
            taskList = tasks;
            return tasks.stream()
                    .filter(task ->  isAfterThisMonth(task.getDatetime()) == true)
                    .collect(Collectors.toList());
        });
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
    public Completable delete(int position){
        return taskDao.delete(taskList.get(position));
    }

}
