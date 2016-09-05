package com.example.tjgudeman.motivational_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EnterBirthDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_birth_day);

        //Create Instance so we can use the "Name" textBox from Main
        new MainActivity();



        //
        TextView textView11 = (TextView) findViewById(R.id.text1B);
        String nameOfUser=  MainActivity.textBox1.getText().toString();
        String textFor1B = "Great! Alright "+ nameOfUser +" let's get started";
        textView11.setText(textFor1B);
    }
}
