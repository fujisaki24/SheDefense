package com.example.shedefense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingsActivity extends AppCompatActivity {

    private static final String myPreference = "Camalot";
    RelativeLayout changenamebtn,show_tut,check_permission;
    TextView usrname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        usrname = findViewById(R.id.username);
        changenamebtn = findViewById(R.id.change_name_settings);
        show_tut = findViewById(R.id.show_tut);
        check_permission = findViewById(R.id.check_permission_btn);

        //String Database_Name = sharedPreferences.getString("Username","No");
        loadname();

        changenamebtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(SettingsActivity.this,R.style.MyOpaqueAlertDialog);
                dialogBuilder.setTitle("Change Name");
                final EditText editText = new EditText(getApplicationContext());
                editText.setTextColor(getResources().getColor(R.color.pinkPrimary));
                Typeface font = ResourcesCompat.getFont(getApplicationContext(),R.font.comfortaa_light);
                editText.setTypeface(font);
                editText.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.pinkPrimary)));
                dialogBuilder.setView(editText,50,0,50,0);
                dialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString();
                        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username",name);
                        editor.apply();
                        loadname();

                    }
                })
                        .setNegativeButton("Cancel", null).show();
            }
        });
        show_tut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Ennada Myre",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),IntroActivity.class);
                startActivity(intent);
            }
        });

        check_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Check Permission Feature has not been set!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadname() {
        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
        String Database_Name = sharedPreferences.getString("Username","No");

        usrname.setText(Database_Name);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
}