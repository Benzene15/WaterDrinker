package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    int lastValue=0;
    DatabaseHelper waterDB;
    private Cursor stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterDB = new DatabaseHelper(this);
        boolean insert = waterDB.insertData(1,0,0,0,"");
        if(insert){
            Toast.makeText(getApplicationContext(), "Welcome to the water Drinking app!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_SHORT).show();
        }
        //waterDB.setValues(1,0,0,200,"10/12/2021");
        stats = waterDB.getData();
        stats.moveToFirst();
        System.out.println(stats.getInt(3));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDateandTime = sdf.format(new Date());
        if(!currentDateandTime.equals(stats.getString(4))) {
            String[] oldDateArrayString = stats.getString(4).split("/");
            String[] newDateArrayString = currentDateandTime.split("/");
            int[] oldDataArray = new int[]{Integer.parseInt(oldDateArrayString[0]), Integer.parseInt(oldDateArrayString[1]), Integer.parseInt(oldDateArrayString[2])};
            int[] newDataArray = new int[]{Integer.parseInt(newDateArrayString[0]), Integer.parseInt(newDateArrayString[1]), Integer.parseInt(newDateArrayString[2])};
            if (oldDataArray[1] == newDataArray[1] + 1) {
                waterDB.setValues(stats.getInt(0),stats.getInt(1),stats.getInt(2)+1, stats.getInt(3), currentDateandTime);
            } else if (addToStreak(newDataArray, oldDataArray)) {
                waterDB.setValues(stats.getInt(0),stats.getInt(1),stats.getInt(2)+1, stats.getInt(3), currentDateandTime);
            } else {
                waterDB.setValues(stats.getInt(0),stats.getInt(1),0, stats.getInt(3), currentDateandTime);
            }
        }
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        waterDrank.setText(stats.getString(1));

        EditText streak = (EditText)findViewById(R.id.streak);
        streak.setText(stats.getString(2));

        EditText date = (EditText)findViewById(R.id.Date);
        date.setText(currentDateandTime.toString());

        EditText quote = (EditText)findViewById(R.id.Quote);
        quote.setText("Stay hydrated, \r\n stay happy");

        updatePercentage();
    }
    public void addWater(View view){
        TextInputEditText waterInput = (TextInputEditText) findViewById(R.id.waterAdded);
        if(waterInput.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Have some water!", Toast.LENGTH_SHORT).show();
            return;
        }
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
        waterSoFar -= lastValue;
        lastValue = -lastValue;
        waterDrank.setText(Integer.toString(waterSoFar));
        updatePercentage();
    }
    public void reset(View view){
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        lastValue = -Integer.parseInt(waterDrank.getText().toString());
        waterDrank.setText("0");
        updatePercentage();
    }
    public void updatePercentage(){
        EditText waterPercentage = (EditText)findViewById((R.id.percentComplete));
        EditText waterDrank  =(EditText)findViewById(R.id.waterDrank);
        int waterSoFar = Integer.parseInt(waterDrank.getText().toString());
        //Update the database for how much water has been drank
        waterDB.setValues(stats.getInt(0),waterSoFar,stats.getInt(2), stats.getInt(3), stats.getString(4));

        int percent = (int)Math.min(100,(waterSoFar/(stats.getInt(3)/2.0))*100);
        if(percent==100){
            waterPercentage.setText("Yay!");
        }
        else{
            waterPercentage.setText(Integer.toString(percent)+"%");
        }
    }
    public void switchToStats(View v){
        Intent i = new Intent(this, StatsActivity.class);
        startActivity(i);
    }
    public boolean addToStreak(int[] newArray, int[] oldArray){
        if(newArray[2]>=oldArray[2]+2){
            return false;
        }
        else if(newArray[1]>=oldArray[1]+2){
            return false;
        }
        else if(newArray[0]>=oldArray[0]+2){
            return false;
        }
        if(oldArray[0]==1 && oldArray[1]==31 && newArray[0]==2 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==2 && oldArray[1]==28 && !(isLeapYear(oldArray[2])) && newArray[0]==3 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==2 && oldArray[1]==28 && isLeapYear(oldArray[2]) && newArray[0]==3 && newArray[1]==1){
            return false;
        }
        if(oldArray[0]==3 && oldArray[1]==31 && newArray[0]==4 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==4 && oldArray[1]==30 && newArray[0]==5 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==5 && oldArray[1]==31 && newArray[0]==6 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==6 && oldArray[1]==30 && newArray[0]==7 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==7 && oldArray[1]==31 && newArray[0]==8 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==8 && oldArray[1]==31 && newArray[0]==9 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==9 && oldArray[1]==30 && newArray[0]==10 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==10 && oldArray[1]==31 && newArray[0]==11 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==11 && oldArray[1]==30 && newArray[0]==12 && newArray[1]==1){
            return true;
        }
        if(oldArray[0]==12 && oldArray[1]==31 && newArray[0]==1 && newArray[1]==1){
            return true;
        }
        return false;

    }
    public static boolean isLeapYear(int year) {
        assert year >= 1583; // not valid before this date.
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
