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

       // StringBuffer sb = new StringBuffer(nameOfUser);
        //sb.append(" String Buffer");
        //System.out.println(nameOfUser.length());


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
    }
}
