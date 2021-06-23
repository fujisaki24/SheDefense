 package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

 public class MainActivity extends AppCompatActivity {


     SharedPreferences sharedPreferences;
     ImageView splashbg;
     private static final String myPreference = "Camalot";
    private static int splash_timeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
        splashbg = findViewById(R.id.splash_bg);
        String firstTime = sharedPreferences.getString("FirstTimeInstall","Yes");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firstTime.equals("Yes")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("FirstTimeInstall","No");
                    editor.commit();
                    Intent start = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(start);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
}