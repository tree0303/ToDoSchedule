package com.example.todoschedule.fragment.adapter;

import android.util.Log;

import com.example.todoschedule.database.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public interface AssortmentOfDateTime {

    DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDate nowDate = LocalDate.now();
    LocalTime startTime = LocalTime.of(00,00);
    LocalTime endTime = LocalTime.of(23,59);

    default Boolean isThisDay(String date){
        String startdatetime = LocalDateTime.of(nowDate,LocalTime.now()).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).format(form);
        Log.i("たちつてとs", startdatetime);
        Log.i("たちつてとe", enddatetime);
        Log.i("たちつてとd", date);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;

    }
    default Boolean isThisWeek(String date){
        String startdatetime = LocalDateTime.of(nowDate, startTime).plusDays(1).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).with(DayOfWeek.SATURDAY).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;
    }
    default Boolean isThisMonth(String date){
        String startdatetime = LocalDateTime.of(nowDate,startTime).with(DayOfWeek.SUNDAY).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).withDayOfMonth(1).plusMonths(1).minusDays(1).format(form);
        Log.i("なにぬねの", startdatetime);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;
    }

    default Boolean isAfterThisMonth(String date){
        String startdatetime = LocalDateTime.of(nowDate, startTime).withDayOfMonth(1).plusMonths(1).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0);
        return flag;
    }


    default List<Task> getThisDayTaskList(List<Task> tasklist){
        return tasklist.stream()
                .filter(task -> isThisDay(task.getDatetime()) == true)
                .collect(Collectors.toList());
    }
    default List<Task> getThisWeekTaskList(List<Task> tasklist){
        return tasklist.stream()
                .filter(task ->  isThisWeek(task.getDatetime()) == true)
                .collect(Collectors.toList());
    }
    default List<Task> getThisMonthTaskList(List<Task> tasklist){
        return tasklist.stream()
                .filter(task ->  isThisMonth(task.getDatetime()) == true)
                .collect(Collectors.toList());
    }
    default List<Task> getAfterThisMonthTaskList(List<Task> tasklist){
        return tasklist.stream()
                .filter(task ->  isAfterThisMonth(task.getDatetime()) == true)
                .collect(Collectors.toList());
    }
}
