package com.example.shedefense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL=1;
    private static final int REQUEST_LOCATION=1;
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
                //Toast.makeText(getApplicationContext(),"Hi,is the toast showing?",Toast.LENGTH_SHORT).show();
                if(!Settings.canDrawOverlays(HomeActivity.this))
                {
                    getPermission();
                }
                else
                {
                    Intent intent = new Intent(HomeActivity.this,WidgetService.class);
                    startService(intent);
                    finish();
                }
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
                Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
                startActivity(intent);
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
                .setPositiveButton("No", null)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        }).show();
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
                Intent settings = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(settings);
                finish();
                //Toast.makeText(getApplicationContext(),"Settings not developed",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Nothing here",Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1);

        }
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(!Settings.canDrawOverlays(HomeActivity.this))
            {
                Toast.makeText(this,"Permission denied by User",Toast.LENGTH_LONG).show();
            }
        }
    }
}