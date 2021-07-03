package com.example.shedefense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeActivity extends AppCompatActivity {

    private static final String myPreference = "Camalot";
    Toolbar toolbar;
    TextView data_name;
    RelativeLayout start_btn,contacts_btn,aboutus_btn,exit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //findviewbyids

        start_btn = findViewById(R.id.start_btn);
        contacts_btn = findViewById(R.id.contacts_btn);
        aboutus_btn = findViewById(R.id.aboutus_btn);
        exit_btn = findViewById(R.id.exit_btn);

        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
        String Database_Name = sharedPreferences.getString("Username","No");

        String full_text = "Good Day,\n" + Database_Name;

        //For toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        data_name = findViewById(R.id.userid_text);
        data_name.setText(full_text);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hi,is the toast showing?",Toast.LENGTH_SHORT).show();
            }
        });

        contacts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hi,is the contacts showing?",Toast.LENGTH_SHORT).show();
            }
        });

        aboutus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"The about us page in development",Toast.LENGTH_SHORT).show();
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              exit_app();
            }
        });

    }

    public void exit_app()
    {
        new MaterialAlertDialogBuilder(this,R.style.MyOpaqueAlertDialog)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }
                }).setNegativeButton("No",null)
                .show();
    }

    @Override
    public void onBackPressed() {
        exit_app();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings:
                Toast.makeText(getApplicationContext(),"Settings not developed",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Nothing here",Toast.LENGTH_LONG).show();
        }
        return true;
    }
}