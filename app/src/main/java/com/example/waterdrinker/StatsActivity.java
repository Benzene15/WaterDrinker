package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StatsActivity extends AppCompatActivity {

    DatabaseHelper waterDB;
    private Cursor stats;
    private boolean cleanSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        waterDB = new DatabaseHelper(this);
    }

    public void changeWeight(View v){
        TextInputEditText weightInput = (TextInputEditText) findViewById(R.id.weight);
        String value = weightInput.getText().toString();
        if(value.equals("")){
            Toast.makeText(getApplicationContext(), "Enter a weight", Toast.LENGTH_SHORT).show();
            cleanSave = false;
            return;
        }
        int weight = Integer.parseInt(value);

        if(weight<=0){
            Toast.makeText(getApplicationContext(), "Please enter a positive weight", Toast.LENGTH_SHORT).show();
            cleanSave = false;
            return;
        }
        else{
            stats = waterDB.getData();
            stats.moveToFirst();
            waterDB.setWeight(stats.getInt(0), weight);
            Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
            cleanSave = true;
        }
    }

    public void switchToMain(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void saveExit(View v){
        changeWeight(null);
        if(cleanSave){
            switchToMain(null);
        }
    }
}