package com.example.tjgudeman.motivational_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // New instance
       // new EnterBirthDay();

        //String month = getIntent().getStringExtra("getMonth");
        int month = getIntent().getIntExtra("getMonth",0);

        // ** Pull data from EnterBirthdayClass
        int day = getIntent().getIntExtra("getDay",0);
        String yearString = getIntent().getStringExtra("getYear"); // Cast to int b/c position doesn't help
        int year= Integer.parseInt(yearString);
        System.out.println(month + "\n"+  day + "\n" + year);

        // Fill Calendar with values from Previous Intent
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.YEAR, year);

        //Subtract Days
        calendar.add(Calendar.DAY_OF_MONTH, -280);

        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("MM/dd/yyyy");
        System.out.println(df.format(calendar.getTime()));



        // *** TextBox1 ***
        TextView congrats = (TextView) findViewById(R.id.Congrats);
        congrats.setText("Guess what! Your parents concieved you: ");

        // *** TextBox with results ***
        TextView results = (TextView) findViewById(R.id.date);
        results.setText((df.format(calendar.getTime())));

        // Button @ bottom pf page
        Button moveOn = (Button) findViewById(R.id.moveOn);
        moveOn.setText("Try Again");
        moveOn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
