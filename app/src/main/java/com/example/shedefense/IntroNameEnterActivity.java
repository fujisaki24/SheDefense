package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class IntroNameEnterActivity extends AppCompatActivity {

    ImageButton submit_btn;
    EditText name_txt;

    private static final String myPreference = "Camalot";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_name_enter);
        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);

        submit_btn = findViewById(R.id.submit_btn);
        name_txt = findViewById(R.id.name_text);

        String Database_Name = sharedPreferences.getString("Username","No");

        if(Database_Name.equals("No"))
        {
           submit_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String name = name_txt.getText().toString();
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("Username",name);
                   editor.putString("FirstTimeInstall","No");
                   editor.commit();

                   Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                   startActivity(i);
                   finish();
               }
           });
        }
        else
        {
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        //nothing to go back
    }

}