package com.example.tjgudeman.motivational_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class thatDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_that_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(null);


        TextView results = (TextView) findViewById(R.id.results);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Change Icon of FAB
        fab.setImageDrawable(getResources().getDrawable(R.drawable.home));


        Intent intent = getIntent();
        String temp = intent.getExtras().getString("results");

        System.out.println("This is from the new page:");
//        String a = extras.getString("temp");
        System.out.print("Coming from thatDay.java" + temp);

        results.setText("If you can see this.. then the app broke");
        results.setText(temp);


    }



    }



