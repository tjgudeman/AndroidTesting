package com.example.tjgudeman.motivational_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    public static EditText textBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textBox1 = (EditText) findViewById(R.id.username);
        checkForEmpty();
        textBox1.addTextChangedListener(mTextWatcher);


// *** Textbox number 1 which welcomes the user to the app ****
        TextView text = (TextView) findViewById(R.id.text);
        SpannableString content = new SpannableString("Content");
        String textBox = "Welcome to my Tester App!";
        String textBox2= "Step 1) Please enter your name below.";

        // Way to change format of textbox number 1 in a dynamic way
        SpannableString spanString = new SpannableString(textBox + "\n\n" + textBox2);
        spanString.setSpan(new UnderlineSpan(), 0, textBox.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        text.setText(spanString);




        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),EnterBirthDay.class);
                startActivity(i);

            }

        });






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


    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkForEmpty();

        }
    };

    void checkForEmpty(){
        Button button = (Button) findViewById(R.id.button);
        String name = textBox1.getText().toString();
        if(name.length() < 5){
            button.setEnabled(false);
            button.setText("Enter Name");
        } else {
            button.setEnabled(true);
            button.setText("Contiune to step 2");
        }
    }





}
