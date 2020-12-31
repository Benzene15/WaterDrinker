package com.example.waterdrinker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
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
        EditText  quote = (EditText)findViewById(R.id.Quote);
        quote.setText("Stay hydrated, stay happy");
    }
}
