package com.example.todoschedule.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name ="taskName")
    public String taskName;
    @ColumnInfo(name = "datetime")
    public String datetime;
    @ColumnInfo(name = "returndegree")
    public String returndegree;
    @ColumnInfo(name = "returnnum", defaultValue = "0")
    public int returnnum;
    @ColumnInfo(name = "fintask", defaultValue = "false")
    public boolean fintask;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getReturndegree(String returndegree){
        return this.returndegree;
    }
    public void setReturndegree(String returndegree) {
        this.returndegree = returndegree;
    }

    public int getReturnnum() {
        return returnnum;
    }
    public void setReturnnum(int returnnum) {
        this.returnnum = returnnum;
    }

    public boolean isFintask() {
        return fintask;
    }
    public void setFintask(boolean fintask) {
        this.fintask = fintask;
    }

}
