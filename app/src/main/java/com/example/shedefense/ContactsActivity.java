package com.example.shedefense;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

public class ContactsActivity extends AppCompatActivity {

    public boolean c1=false,c2=false,c3=false;
    private static final String myPreference = "Camalot";
    private static int CONTACT_PERMISSION_CODE = 1;
    private static int CONTACT_PICK_CODE_1 = 1;
    private static int CONTACT_PICK_CODE_2 = 2;
    private static int CONTACT_PICK_CODE_3 = 3;

    ImageButton addbtn1,addbtn2,addbtn3;
    TextView name1,name2,name3;
    TextView num1,num2,num3;
    String name_1,name_2,name_3,num_1,num_2,num_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        addbtn1 = findViewById(R.id.addbtn1);
        addbtn2 = findViewById(R.id.addbtn2);
        addbtn3 = findViewById(R.id.addbtn3);


        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);

        loadData();

        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1=true;
                if(checkcontactpermission() == true)
                {
                 pickContactIntent(CONTACT_PICK_CODE_1);
                }
                else
                {
                    requestContactpermission();
                }
            }
        });

        addbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2=true;
                if(checkcontactpermission() == true)
                {
                    pickContactIntent(CONTACT_PICK_CODE_2);
                }
                else
                {
                    requestContactpermission();
                }
            }
        });

        addbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3=true;
                if(checkcontactpermission() == true)
                {
                    pickContactIntent(CONTACT_PICK_CODE_3);
                }
                else
                {
                    requestContactpermission();
                }
            }
        });

    }

    public void pickContactIntent(int code)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,code);
    }

    public boolean checkcontactpermission()
    {
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestContactpermission()
    {
        if (ContextCompat.checkSelfPermission(ContactsActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
        name_1 = sharedPreferences.getString("Name 1","Name:");
        name_2 = sharedPreferences.getString("Name 2","Name:");
        name_3 = sharedPreferences.getString("Name 3","Name:");
        num_1 = sharedPreferences.getString("Num 1","Number:");
        num_2 = sharedPreferences.getString("Num 2","Number:");
        num_3 = sharedPreferences.getString("Num 3","Number:");

        name1.setText(name_1);
        num1.setText(num_1);
        name2.setText(name_2);
        num2.setText(num_2);
        name3.setText(name_3);
        num3.setText(num_3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CONTACT_PERMISSION_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
              if(c1==true)
              {
                  pickContactIntent(CONTACT_PICK_CODE_1);
              }
              else if(c2==true)
              {
                  pickContactIntent(CONTACT_PICK_CODE_2);
              }
              else if(c3==true)
              {
                  pickContactIntent(CONTACT_PICK_CODE_3);
              }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            if(requestCode == CONTACT_PICK_CODE_1)
            {
                Cursor cr1,cr2;
                Uri uri = data.getData();

                cr1 = getContentResolver().query(uri,null,null,null,null);
                    if (cr1.moveToFirst()) {

                        String contact_id = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts._ID));
                        String contact_name = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String idresults = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        name1.setText(contact_name);
                        int idResulthold = Integer.parseInt(idresults);
                        if(idResulthold == 1)
                        {
                            try {

                                cr2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id,
                                        null,
                                        null);
                                cr2.moveToNext();

                                String contact_number = cr2.getString(cr2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                num1.setText(contact_number);
                                SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Name 1",contact_name);
                                editor.putString("Num 1",contact_number);
                                editor.commit();
                                loadData();
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                        }
                    }
                }

                    c1=false;
                }

            else if(requestCode == CONTACT_PICK_CODE_2)
            {
                Cursor cr1,cr2;
                Uri uri = data.getData();

                cr1 = getContentResolver().query(uri,null,null,null,null);
                if (cr1.moveToFirst()) {

                    String contact_id = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts._ID));
                    String contact_name = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String idresults = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    name2.setText(contact_name);
                    int idResulthold = Integer.parseInt(idresults);
                    if(idResulthold == 1)
                    {
                        try {

                            cr2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id,
                                    null,
                                    null);
                            cr2.moveToNext();

                            String contact_number = cr2.getString(cr2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            num2.setText(contact_number);
                            SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Name 2",contact_name);
                            editor.putString("Num 2",contact_number);
                            editor.commit();
                            loadData();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                c2=false;
            }
            else if(requestCode == CONTACT_PICK_CODE_3)
            {
                Cursor cr1,cr2;
                Uri uri = data.getData();

                cr1 = getContentResolver().query(uri,null,null,null,null);
                if (cr1.moveToFirst()) {

                    String contact_id = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts._ID));
                    String contact_name = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String idresults = cr1.getString(cr1.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    name3.setText(contact_name);
                    int idResulthold = Integer.parseInt(idresults);
                    if(idResulthold == 1)
                    {
                        try {

                            cr2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contact_id,
                                    null,
                                    null);
                            cr2.moveToNext();

                            String contact_number = cr2.getString(cr2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            num3.setText(contact_number);
                            SharedPreferences sharedPreferences = getSharedPreferences(myPreference,MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Name 3",contact_name);
                            editor.putString("Num 3",contact_number);
                            editor.commit();
                            loadData();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                c3=false;
            }

            }
        }
}