package com.example.tjgudeman.motivational_project;

import android.content.Intent;
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

import java.util.Arrays;

public class EnterBirthDay extends AppCompatActivity {

   final String dayArray31[]={"--","1","2","3" ,"4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};
    final String dayArray30[]= Arrays.copyOf(dayArray31, dayArray31.length-1);
    final String dayArray28[]= Arrays.copyOf(dayArray31, dayArray31.length-3);

    public String daySelected= " ";
    public String monthSelected= " ";
    public String yearSelected= " ";
    public int monthPos= -1;
    public int dayPos= -1;
    public String year= " ";

    public static Spinner month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_birth_day);

        //Create Instance so we can use the "Name" textBox from Main
        new MainActivity();

        //Arrays for Month, Day, Year
        String monthArray[]={"--","January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

    //    final String dayArray31[]={"--","1","2","3" ,"4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
     //           "21","22","23","24","25","26","27","28","29","30","31"};
        final String dayArray30[]= Arrays.copyOf(dayArray31, dayArray31.length-1);
        final String dayArray29[]= Arrays.copyOf(dayArray31, dayArray31.length-2);


        String yearArray[] = {"--", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999"};




        // *** Text box 1 ***
        TextView textView11 = (TextView) findViewById(R.id.text1B);
        String nameOfUser=  MainActivity.textBox1.getText().toString();

        int tempLength = nameOfUser.length()-1;
        char tempChar = nameOfUser.charAt(tempLength);

        // Checking to see if name has multiple spaces (So the coma does not look out of place)
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


        // *** Spinner information (Month) ***
        final Spinner monthSpinner= (Spinner) findViewById(R.id.month);
        ArrayAdapter monthAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, monthArray);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        // ** Set Text for day textView **
        TextView dayTextView= (TextView) findViewById(R.id.dayText);
        dayTextView.setText("Day");

        // *** Spinner Info (Day) ***
        final Spinner daySpinner= (Spinner) findViewById(R.id.day);

        int temp = 0;
        System.out.println(monthSpinner.getSelectedItem());
        ArrayAdapter dayAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, dayArray31);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        if(monthSelected.equalsIgnoreCase("September")){
            dayAdapter.add("that");
            dayAdapter.notifyDataSetChanged();
            System.out.println(" change");
        } else {
            if (monthSelected.equals("poo")) {
                dayAdapter.add("default");
                dayAdapter.notifyDataSetChanged();
                System.out.println("No change");
            }

        }


        // ** *Set Text for year Textview ***
        TextView yearTextView = (TextView) findViewById(R.id.yearText);
        yearTextView.setText("Year");

        // *** Spinner Info (year) ***
        final Spinner yearSpinner= (Spinner) findViewById(R.id.year);
        ArrayAdapter yearAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearArray);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


        // *** Button (Default not Enabled b/c verified in checkForCompletion() ***
        Button button = (Button) findViewById(R.id.moveOn);
        button.setEnabled(false);
        button.setText("Enter Birthday");

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),Results.class);
                i.putExtra("getDay",dayPos);
                i.putExtra("getMonth", monthPos);
                i.putExtra("getYear", year);
                startActivity(i);
            }
           //

        });






        // ********************************************************************************
        // Enabling Spinners to remember info and enable/disable depending on input
        // ********************************************************************************

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 monthSelected = parent.getItemAtPosition(position).toString();
                if(monthSelected.equalsIgnoreCase("--")){
                    daySpinner.setEnabled(false);
                    daySpinner.setSelection(0);

                    yearSpinner.setSelection(0);
                    yearSpinner.setEnabled(false);
                }
                else{
                    daySpinner.setEnabled(true);
                    monthPos = position;


                    // *** Logic to select correct days for month (ie: September = 30) ***
                    if(position % 2 == 1) { //Odd months
                        if (position <= 7) { //Jan, March, May,
                            reCreateMonthSpinner(dayArray31);
                        } else {
                            reCreateMonthSpinner(dayArray30); // September, November
                        }
                    }
                    else // Even months
                        if(position >= 8) //
                            reCreateMonthSpinner(dayArray31); //August, October, December
                    else
                            if(position == 2)
                                reCreateMonthSpinner(dayArray28); // February
                    else
                                reCreateMonthSpinner(dayArray30); // April, June
                    }
            }
            // Auto generated. Not needed, with spinner there is always something selected
            public void onNothingSelected(AdapterView<?> parent) {
                daySpinner.setEnabled(false);
            }
        });



        // ** Logic for selecting Year (so the user has to enter days before year) **
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daySelected = parent.getItemAtPosition(position).toString();
                if(daySelected.equalsIgnoreCase("--")){
                    yearSpinner.setEnabled(false);
                    yearSpinner.setSelection(0);
                }
                else {
                    yearSpinner.setEnabled(true);
                    dayPos = position;
                }

            }

            // Auto generated. Not needed, with spinner b/c there is always something selected
            public void onNothingSelected(AdapterView<?> parent) {
                yearSpinner.setEnabled(false);
            }
        });

        // ** Logic for enabling button **
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearSelected = parent.getItemAtPosition(position).toString();
                if(yearSelected.equalsIgnoreCase("--")){
                    inComplete();

                }
                else {
                    checkForCompletion();
                    year = yearSelected;
                }
            }

            // Auto generated. Not needed, with spinner b/c there is always something selected
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    // *** This class will check to see if all 3 spinners have been entered with valid data ***
    void checkForCompletion(){
        Button button = (Button) findViewById(R.id.moveOn);
        button.setEnabled(true);
        button.setText("Contiune to step 3");
        }

    void inComplete(){
        Button button = (Button) findViewById(R.id.moveOn);
        button.setEnabled(false);
        button.setText("Enter Birthday");

    }


    // *** This class will recreate the day spinner so the correct days areavailable depending on the month
    void reCreateMonthSpinner(String[] a){
        Spinner daySpinner = (Spinner) findViewById(R.id.day);
        ArrayAdapter dayAdapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, a);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
    }
    }



