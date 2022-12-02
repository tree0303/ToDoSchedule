package com.example.todoschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.todoschedule.fragment.DateFragment;
import com.example.todoschedule.fragment.TimeFragment;
import com.example.todoschedule.viewmodel.TaskViewModel;

import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class NewtaskActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, TextWatcher {


    private EditText task_input;
    private EditText date_input;
    private EditText time_input;
    private Button add_button;
    private Spinner returnnum_input;
    private Spinner return_input;
    private LinearLayout mainLayout;
    private InputMethodManager inputMethodManager;
    private DialogFragment datedialog;
    private DialogFragment timedialog;
    private TaskViewModel taskViewModel;
    private static final String TAG = "NewtaskActivity";
    private final CompositeDisposable disposable = new CompositeDisposable();


    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        Calendar calendar = Calendar.getInstance();
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        datedialog = new DateFragment();
        timedialog = new TimeFragment();


        //inputMethodManager
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //layout
        mainLayout = findViewById(R.id.mainlayout);
        mainLayout.setOnTouchListener(this::onTouch);
        //Button
        add_button = findViewById(R.id.addbutton);
        add_button.setOnClickListener(this::onClick);
        //edittext
        task_input = findViewById(R.id.task_input);
        date_input = findViewById(R.id.date_input);
        time_input = findViewById(R.id.time_input);

        task_input.addTextChangedListener( this);

        date_input.setOnClickListener(this::onClick);
        date_input.setOnFocusChangeListener(this::onFocusChange);
        date_input.setKeyListener(null);
        //日付初期値
        date_input.setText(String.format("%d/%02d/%02d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH)));


        time_input.setOnClickListener(this::onClick);
        time_input.setOnFocusChangeListener(this::onFocusChange);
        time_input.setKeyListener(null);

        //spinner
        returnnum_input = findViewById(R.id.returnnum_input);
        return_input = findViewById(R.id.return_input);
        //return_input
        ArrayAdapter<CharSequence> return_array_adapter = ArrayAdapter.createFromResource(this,
                R.array.return_array, android.R.layout.simple_spinner_item);
        return_array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return_input.setAdapter(return_array_adapter);
        return_input.setOnItemSelectedListener(this);
        //returnnum_input
        ArrayAdapter<CharSequence> returnnum_array_adapter = ArrayAdapter.createFromResource(this,
                R.array.returnnum_array, android.R.layout.simple_spinner_item);
        returnnum_array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        returnnum_input.setAdapter(returnnum_array_adapter);
        returnnum_input.setOnItemSelectedListener(this);
    }

    //edittext日付と時間のダイアログ表示
    public void onFocusChange(View view, boolean hasFocus){
        switch (view.getId()){
            //日付のダイアログ表示
            case R.id.date_input:
                if (hasFocus) datedialog.show(getFragmentManager(), "datedialog");
                break;
                //時間のダイアログ表示
            case R.id.time_input:
                if (hasFocus) timedialog.show(getFragmentManager(), "timedialog");
                break;
        }
    }

    //タスク名が入力されたらボタンを押せるようにする
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
    @Override
    public void afterTextChanged(Editable editable){
        String str = editable.toString().replaceAll(" |　","");
        if (!str.equals("")){
            add_button.setEnabled(true);
        }else {
            add_button.setEnabled(false);
        }
    }


    //button
    @SuppressLint("NonConstantResourceId")
    public void onClick(@NonNull View view) {
        switch (view.getId()){
            case R.id.addbutton:
                StringBuilder datetime = new StringBuilder();
                datetime.append(date_input.getText().toString());
                datetime.append(" ");
                datetime.append(time_input.getText().toString());
                Log.i("あああああ", datetime.toString());
                taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
                disposable.add(taskViewModel.insert(task_input.getText().toString(), datetime.toString(),
                        return_input.toString(), Integer.valueOf(returnnum_input.getId())+1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {}, throwable -> Log.e(TAG,
                                "Unable to insert.", throwable)));
                Intent mainActivity = new Intent(getApplication(), MainActivity.class);
                if (mainActivity.resolveActivity(getPackageManager()) != null){
                    startActivity(mainActivity);
                }
                break;
                //日付のダイアログ表示
            case R.id.date_input:
                datedialog.show(getFragmentManager(), "datedialog");
                break;
                //時間のダイアログ表示
            case R.id.time_input:
                timedialog.show(getFragmentManager(), "timedialog");
                break;
        }
    }


    //spinnerの処理
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            //繰り返すか繰り返さないか
            case R.id.return_input:
                switch ((int) adapterView.getSelectedItemId()){
                    case 0://繰り返さない
                        //日にち選択のspinner非表示
                        returnnum_input.setVisibility(View.INVISIBLE);
                        break;
                    case 1://時間おき
                        //日にち選択のspinner表示
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 2://日おき
                        //日にち選択のspinner表示
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 3://週おき
                        //日にち選択のspinner表示
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 4://月おき
                        //日にち選択のspinner表示
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                }
                break;
                //数値が選択された時の処理
            case R.id.returnnum_input:
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        switch (adapterView.getId()) {
            case R.id.return_input:
                break;
            case R.id.returnnum_input:
                break;
        }
    }
    //画面がタップしたとき
    public boolean onTouch(View view, MotionEvent motionEvent){
        //キーボード消す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景フォーカス
        mainLayout.requestFocus();
        return true;
    }
    //日付ダイアログがOK押されたとき
    @SuppressLint("DefaultLocale")
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        date_input.setText(String.format("%d/%02d/%02d",year,month+1,day));
        ((DateFragment) datedialog).setYear(year);
        ((DateFragment) datedialog).setMonth(month);
        ((DateFragment) datedialog).setDay(day);
    }

    //時間ダイアログがOK押されたとき
    @SuppressLint("DefaultLocale")
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        time_input.setText(String.format("%02d:%02d",hour,minute));
        ((TimeFragment) timedialog).setHour(hour);
        ((TimeFragment) timedialog).setMinute(minute);
    }

    @Override
    public void onStop(){
        super.onStop();
        disposable.clear();
    }
}