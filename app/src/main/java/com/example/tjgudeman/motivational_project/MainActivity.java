package com.example.tjgudeman.motivational_project;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Textbox number 1 which welcomes the user to the app
        TextView text = (TextView) findViewById(R.id.text);
        SpannableString content = new SpannableString("Content");
        String textBox = "Welcome to my Tester App!";
        String textBox2= "Please enter your name below.";

        // Way to change format of textbox number 1 in a dynamic way
        SpannableString spanString = new SpannableString(textBox + "\n\n" + textBox2);
        spanString.setSpan(new UnderlineSpan(), 0, textBox.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        text.setText(spanString);


 //       TextView text2 = (TextView) findViewById(R.id.text2);
//        String textBox2= "Please enter your name below.";

//        SpannableString spanString2 = new SpannableString("Please"); //Hardcoded because Android Studio was giving me problems
//        spanString.setSpan(new UnderlineSpan(), 0, spanString2.length(), 0);
//        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString2.length(), 0);
//        text2.setText(spanString2);



        EditText editText = (EditText) findViewById(R.id.username);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean worked = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    //sendMessage();
                    worked = true;
                }
                return worked;
            }
        });



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
