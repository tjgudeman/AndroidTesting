package com.example.tjgudeman.motivational_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        int tempLength = nameOfUser.length()-1;
        char tempChar = nameOfUser.charAt(tempLength);

        //Checking to see if name has multiple spaces (So the coma does not look out of place)
        while(tempChar == ' '){
            tempLength -= 1;
            nameOfUser = nameOfUser.substring(0, tempLength+1);
            tempChar= nameOfUser.charAt(tempLength);
        }

        String textFor1B = "Great! Alright " + nameOfUser + "," + " for step #2 please enter " +
                "your date of birth";
        textView11.setText(textFor1B);

        // *** Set Text for month textView ****
        TextView monthTextView = (TextView) findViewById(R.id.monthText);
        monthTextView.setText("Month");


        //Arrays for Month, Day, Year
        String monthArray[]={"--","January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"};

        String dayArray[]={"1","2"};

        // Spinner information (Month)
        Spinner monthSpinner= (Spinner) findViewById(R.id.month);
        ArrayAdapter monthAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, monthArray);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        // ** Set Text for day textView **
        TextView dayTextView= (TextView) findViewById(R.id.dayText);
        dayTextView.setText("Day");

        // Spinner Info (Day)
        final Spinner daySpinner= (Spinner) findViewById(R.id.day);
        ArrayAdapter dayAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, dayArray);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
       // daySpinner.setEnabled(false);



        // ** Set Text for year Textview
        TextView yearTextView = (TextView) findViewById(R.id.yearText);
        yearTextView.setText("Year");










        // ** Logic for selecting days (because different months have a different # of days **
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String monthSelected = parent.getItemAtPosition(position).toString();
                if(monthSelected.equalsIgnoreCase("--")){
                    daySpinner.setEnabled(false);
                }
                else{
                    daySpinner.setEnabled(true);
                }
                System.out.println(monthSelected);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                daySpinner.setEnabled(false);
            }
        });



    }
}
