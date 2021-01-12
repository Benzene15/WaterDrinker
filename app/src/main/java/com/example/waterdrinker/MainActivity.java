package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import  java.lang.Math;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int startingValue = 0;
    int lastValue=0;
    int WEIGHT=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        waterDrank.setText("0");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateandTime = sdf.format(new Date());
        EditText date = (EditText)findViewById(R.id.Date);
        date.setText(currentDateandTime.toString());
        EditText quote = (EditText)findViewById(R.id.Quote);
        quote.setText("Stay hydrated, stay happy");
        updatePercentage();
    }
    public void addWater(View view){
        TextInputEditText waterInput = (TextInputEditText) findViewById(R.id.waterAdded);
        int waterAdded = Integer.parseInt(waterInput.getText().toString());
        if(waterAdded<0){
            Toast.makeText(getApplicationContext(), "Can't Drink negative water", Toast.LENGTH_SHORT).show();
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
            waterPercentage.setText(Integer.toString(percent));
        }
    }
}
