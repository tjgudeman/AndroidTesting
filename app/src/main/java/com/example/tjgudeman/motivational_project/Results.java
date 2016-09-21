package com.example.tjgudeman.motivational_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.SAXParserFactory;

public class Results extends AppCompatActivity {


    public TextView thatDay;
    public String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // New instance
        // new EnterBirthDay();

        //String month = getIntent().getStringExtra("getMonth");
        final int month = getIntent().getIntExtra("getMonth", 0);

        // ** Pull data from EnterBirthdayClass
        int day = getIntent().getIntExtra("getDay", 0);
        final String yearString = getIntent().getStringExtra("getYear"); // Cast to int b/c position doesn't help
        int year = Integer.parseInt(yearString);
        System.out.println(month + "\n" + day + "\n" + year);

        // Fill Calendar with values from Previous Intent
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.YEAR, year);

        //Subtract Days
        calendar.add(Calendar.DAY_OF_MONTH, -280);

        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("MM/dd/yyyy");
        System.out.println(df.format(calendar.getTime()));


        // *** TextBox1 ***
        TextView congrats = (TextView) findViewById(R.id.Congrats);
        congrats.setText("Guess what? You were concieved: ");

        // *** TextBox with results ***
        TextView results = (TextView) findViewById(R.id.date);
        results.setText((df.format(calendar.getTime())));


      //  *** We'll see ****
        thatDay =(TextView)(findViewById(R.id.thatDay));
        thatDay.setText(temp);
//



        // Button @ bottom of page
        Button moveOn = (Button) findViewById(R.id.moveOn);
        moveOn.setText("Try Again");
        moveOn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }

        });




// **********************************************************************************************
//  Assign values of month and day as a string to they could be given to the URL
// *******************************************************************************************************
        final String monthString=getMonth(month);

        String tempA = String.valueOf(day);
        if(day <=9) {                           // URL needs to add a 0 to the day if it is in single digits
            tempA = tempA.format("%02d",day); //add a 0 so it takes up to the 10's place
        }
        final String dayString= tempA;
        final int yearString2= calendar.get(Calendar.YEAR);

        System.out.println(monthString + "  " + dayString + "  " + yearString2);



        // **** Floating action button is invisible so we don't have to worry about it *******
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // **** Starting new thread so we can pull info from web ****
        final StringBuilder wholeString= new StringBuilder(" ");
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    URL url;
                        InputStream is = null;
                        BufferedReader br;
                        String line; //



                        try {
                            String a="September";
                            url = new URL("http://takemeback.to/" + dayString + "-" + monthString + "-"+yearString2 +"#");
                            System.out.print(url);
                            is = url.openStream();  // throws an IOException
                            br = new BufferedReader(new InputStreamReader(is));
                            String lineNeeded= "<div itemscope itemtype=\"http://schema.org/Event\"><meta itemprop=\"startDate\" content=\"1994-09-19\"><meta itemprop=\"Description\" content=\"What happened in the world on that day\"><meta itemprop=\"name\" content=\"World Event\"><p>";


                            // Reads whole file/site
                            while ((line = br.readLine()) != null) {
                                if(line.contains("content=\"What happened in the world on that day\"><meta itemprop=\"name\" content=\"World Event\"><p>")) //Will catch line we care about
                                    while (!(line.contains("</p></div>"))) { //Will go until we hit the lines we don't car about
                                        String temp= line.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
                                        wholeString.append(temp);
                                        line= br.readLine();
                                    }

                            }
                        } catch (MalformedURLException mue) {
                            mue.printStackTrace();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        } finally {
                            try {
                                if (is != null) is.close();
                            } catch (IOException ioe) {
                                // nothing to see here
                            }
                        }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


                temp=wholeString.toString();
                temp =trimString(temp);
                System.out.println(temp);
//                thatDay =(TextView)(findViewById(R.id.thatDay));
//                thatDay.setText(temp);
            }
        });
        thread.start();
    }




    public String trimString(String a){
        String notNeeded= " But much more happened that day: find out below..";
        while(a.contains(" , ")){
            a=a.replace(" , ",", ");
        }

        while(a.contains(" . ")){
            a=a.replace(" . ",". ");
        }

        while(a.contains(notNeeded)){
            a=a.replace(notNeeded," ");
        }

        return a;
    }

    public String getMonth(int month) {

        System.out.println("this");
        return new DateFormatSymbols().getMonths()[month-1];
    }


}






