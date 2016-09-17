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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.SAXParserFactory;

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
        int month = getIntent().getIntExtra("getMonth", 0);

        // ** Pull data from EnterBirthdayClass
        int day = getIntent().getIntExtra("getDay", 0);
        String yearString = getIntent().getStringExtra("getYear"); // Cast to int b/c position doesn't help
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

        // Button @ bottom pf page
        Button moveOn = (Button) findViewById(R.id.moveOn);
        moveOn.setText("Try Again");
        moveOn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }

        });


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
                        String line;

                        try {
                            url = new URL("http://takemeback.to/19-September-1994");
                            is = url.openStream();  // throws an IOException
                            br = new BufferedReader(new InputStreamReader(is));
                            String lineNeeded= "<div itemscope itemtype=\"http://schema.org/Event\"><meta itemprop=\"startDate\" content=\"1994-09-19\"><meta itemprop=\"Description\" content=\"What happened in the world on that day\"><meta itemprop=\"name\" content=\"World Event\"><p>";


                            // Reads whole file/site
                            while ((line = br.readLine()) != null) {
                                if(line.equals(lineNeeded)) //Will catch line we care about
                                    while (!(line.contains("</p></div>"))) { //Will go until we hit the lines we don't car about
                                        System.out.println(line);
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
            }
        });

        thread.start();


    }



//    private class RetrieveFeedTask extends AsyncTask<String, Void, RSSFeed> {
//
//        private Exception exception;
//
//        protected RSSFeed doInBackground(String... urls) {
//            try {
//                URL url = new URL(urls[0]);
//                SAXParserFactory factory = SAXParserFactory.newInstance();
//                SAXParser parser = factory.newSAXParser();
//                XMLReader xmlreader = parser.getXMLReader();
//                RssHandler theRSSHandler = new RssHandler();
//                xmlreader.setContentHandler(theRSSHandler);
//                InputSource is = new InputSource(url.openStream());
//                xmlreader.parse(is);
//
//                return theRSSHandler.getFeed();
//            } catch (Exception e) {
//                this.exception = e;
//
//                return null;
//            }
//        }
//
//        protected void onPostExecute(RSSFeed feed) {
//            // TODO: check this.exception
//            // TODO: do something with the feed
//        }
//    }




//    public void urlReader() {
//        URL url;
//        InputStream is = null;
//        BufferedReader br;
//        String line;
//
//        try {
//            url = new URL("http://takemeback.to/19-September-1994");
//            is = url.openStream();  // throws an IOException
//            br = new BufferedReader(new InputStreamReader(is));
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (MalformedURLException mue) {
//            mue.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (is != null) is.close();
//            } catch (IOException ioe) {
//                // nothing to see here
//            }
//        }
//
//
//    }
}






