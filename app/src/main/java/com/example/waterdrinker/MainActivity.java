package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import  java.lang.Math;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {
    int startingValue = 0;
    int lastValue=0;
    int WEIGHT=200;
    DatabaseHelper waterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterDB = new DatabaseHelper(this);
        boolean insert = waterDB.insertData(1,10,10,10,"10/12/2021");
        if(insert){
            Toast.makeText(getApplicationContext(), "Welcome to the water Drinking app!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_SHORT).show();
        }
        waterDB.setValues(1,0,0,0,"10/13/2021");
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        waterDrank.setText("0");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateandTime = sdf.format(new Date());
        EditText date = (EditText)findViewById(R.id.Date);
        date.setText(currentDateandTime.toString());
        EditText quote = (EditText)findViewById(R.id.Quote);
        quote.setText("Stay hydrated, \r\n stay happy");
        updatePercentage();
    }
    public void addWater(View view){
        TextInputEditText waterInput = (TextInputEditText) findViewById(R.id.waterAdded);
        int waterAdded = Integer.parseInt(waterInput.getText().toString());
        if(waterAdded<=0){
            Toast.makeText(getApplicationContext(), "Have some water!", Toast.LENGTH_SHORT).show();
        }
        else{
            EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
            int waterSoFar = Integer.parseInt(waterDrank.getText().toString());
            waterDrank.setText("0");
            waterSoFar += waterAdded;
            lastValue = waterAdded;
            waterDrank.setText(Integer.toString(waterSoFar));
            updatePercentage();
        }
    }
    public void undo(View view){
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        int waterSoFar = Integer.parseInt(waterDrank.getText().toString());
        waterSoFar-=lastValue;
        lastValue=0;
        waterDrank.setText(Integer.toString(waterSoFar));
        updatePercentage();
    }
    public void reset(View view){
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        waterDrank.setText("0");
        lastValue = 0;
        updatePercentage();
    }
    public void updatePercentage(){
        EditText waterPercentage = (EditText)findViewById((R.id.percentComplete));
        EditText waterDrank  =(EditText)findViewById(R.id.waterDrank);
        int waterSoFar = Integer.parseInt(waterDrank.getText().toString());
        int percent = (int)Math.min(100,(waterSoFar/(WEIGHT/2.0))*100);
        if(percent==100){
            waterPercentage.setText("Yay!");
        }
        else{
            waterPercentage.setText(Integer.toString(percent)+"%");
        }
    }
}
