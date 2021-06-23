package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private static final String myPreference = "Camalot";



    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);

        textView = findViewById(R.id.detail);

        String name = sharedPreferences.getString("Username","");

        textView.setText(name);
    }
}