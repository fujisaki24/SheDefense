package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cuberto.liquid_swipe.LiquidPager;

public class IntroActivity extends AppCompatActivity {

    LiquidPager pager;
    ViewPager viewPager;
    View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_intro);

        pager=findViewById(R.id.pager);
        viewPager=new ViewPager(getSupportFragmentManager(),1);
        pager.setAdapter(viewPager);
    }
}