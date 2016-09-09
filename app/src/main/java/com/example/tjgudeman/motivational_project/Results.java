package com.example.tjgudeman.motivational_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // New instance
        new EnterBirthDay();

        String month = getIntent().getStringExtra("getMonth");

        String day = getIntent().getStringExtra("getDay");
        String year = getIntent().getStringExtra("getYear");
        System.out.println(month + "\n"+  day + "\n" + year);


//        String nameOfUser=  EnterBirthDay.month.getSelectedItem().toString();
//        System.out.println(nameOfUser);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
