package com.example.todoschedule.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Calendar;

public class TimeFragment extends DialogFragment {

    private int hour;
    private int minute;

    public TimeFragment(){
        final Calendar calendar = Calendar.getInstance();
        hour = 00;
        minute = 00;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, true);

        return timePickerDialog;
    }
    public void setHour(int h){
        hour = h;
    }
    public void setMinute(int m){
        minute = m;
    }
}
