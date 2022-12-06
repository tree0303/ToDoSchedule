package com.example.todoschedule.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Calendar;

public class DateFragment extends DialogFragment {

    private int year;
    private int month;
    private int day;

    public DateFragment() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }

    public void setYear(int y){
        year = y;
    }
    public void setMonth(int m){
        month = m;
    }
    public void setDay(int d){
        day = d;
    }


}
