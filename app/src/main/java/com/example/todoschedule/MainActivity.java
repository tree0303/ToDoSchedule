package com.example.todoschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newaddbutton = (Button) findViewById(R.id.newaddbutton);
        Button deletebutton = (Button) findViewById(R.id.deletebutton);

        newaddbutton.setOnClickListener(this::onClick);
        deletebutton.setOnClickListener(this::onClick);
    }

    @SuppressLint({"NonConstantResourceId", "QueryPermissionsNeeded"})
    private void onClick(View view) {
        switch (view.getId()){
            case R.id.newaddbutton:
                Intent newtask = new Intent(getApplication() , NewtaskActivity.class);
                if (newtask.resolveActivity(getPackageManager()) != null){
                    startActivity(newtask);
                }
                break;
            case R.id.deletebutton:

                break;

        }
    }


}