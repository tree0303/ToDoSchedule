package com.example.todoschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.stream.IntStream;

public class NewtaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView newadd_text;
    private TextView task_text;
    private TextView datetime_text;
    private TextView return_text;
    private Button add_button;
    private EditText task_input;
    private EditText datetime_input;
    private Spinner returnnum_input;
    private Spinner return_input;
    ArrayAdapter<CharSequence> returnnum_array_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);

        Intent newtask = this.getIntent();
        //text, button
        newadd_text = (TextView) findViewById(R.id.newadd_text);
        task_text = (TextView) findViewById(R.id.task_text);
        datetime_text = (TextView) findViewById(R.id.datetime_text);
        return_text = (TextView) findViewById(R.id.return_text);
        add_button = (Button) findViewById(R.id.addbutton);
        add_button.setOnClickListener(this::onClick);
        //edittext
        task_input = (EditText) findViewById(R.id.task_input);
        datetime_input = (EditText) findViewById(R.id.datetime_input);
        //spinner
        returnnum_input = (Spinner) findViewById(R.id.returnnum_input);
        return_input = (Spinner) findViewById(R.id.return_input);
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

    public void onClick(@NonNull View view) {
        switch (view.getId()){
            case R.id.addbutton:
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.return_input:
                switch ((int) adapterView.getSelectedItemId()){
                    case 0:
                        returnnum_input.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        returnnum_input.setVisibility(View.VISIBLE);
                        break;
                }
                break;

            case R.id.returnnum_input:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        switch (adapterView.getId()) {
            case R.id.return_input:
                break;
            case R.id.returnnum_input:
                break;
        }
    }
}