package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int startingValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText waterDrank = (EditText)findViewById(R.id.waterDrank);
        waterDrank.setText("0");
        Date currentTime = Calendar.getInstance().getTime();
        EditText date = (EditText)findViewById(R.id.Date);
        date.setText(currentTime.toString());
        EditText quote = (EditText)findViewById(R.id.Quote);
        quote.setText("Stay hydrated, stay happy");
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
            waterSoFar += waterAdded;
            waterDrank.setText(Integer.toString(waterSoFar));
        }
    }
}
