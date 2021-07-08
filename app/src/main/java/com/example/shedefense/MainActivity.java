 package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.ImageView;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

 public class MainActivity extends AppCompatActivity {


     SharedPreferences sharedPreferences;
     ImageView splashbg;
     private static final int REQUEST_PHONE_CALL=1;
     private static final int REQUEST_LOCATION=1;
     private static final String myPreference = "Camalot";
     private static int splash_timeout = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
        splashbg = findViewById(R.id.splash_bg);
        String firstTime = sharedPreferences.getString("FirstTimeInstall","Yes");
        getPermission();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firstTime.equals("Yes")) {
                    Intent start = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(start);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
                    finish();
                }
                else
                {
                    Intent start2 = new Intent(getApplicationContext(), IntroNameEnterActivity.class);
                    startActivity(start2);
                    finish();
                }
            }
        },splash_timeout);
    }

     @Override
     public void onBackPressed() {
         //do nothing
     }

     public void getPermission() {
         if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
             ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);

         if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
             ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
         }

     }
 }