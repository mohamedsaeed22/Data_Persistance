package com.example.data_persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Internal_Storage extends AppCompatActivity {

    EditText et_username, et_pass, et_age;
    Button btn_save, btn_getData;

    public static final String FILE_NAME = "USER_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_pass);
        et_age = findViewById(R.id.et_age);
        btn_save = findViewById(R.id.btn_savedata);
        btn_getData = findViewById(R.id.btn_getdata);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_username.getText().toString();
                String pass = et_pass.getText().toString();
                String age = et_age.getText().toString();

                // بالنسبه للملف داتا راحه او هكتب عليه بالنسبه للطبيق دى outPut
                //  الملف لو هقرا منه او هاخد منه داتا  بالنسبه للتطبيق دى Input

                //  عشان اكتب فى الملف

                try {
                    File f = new File(getFilesDir(), FILE_NAME);
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(f, true); //append عشان يضيف  ع القديم


//                    FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(username);
                    pw.println(pass);
                    pw.println(age);
                    pw.close();
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                et_username.setText("");
                et_pass.setText("");
                et_age.setText("");

            }
        });

        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    String allText = "";
                    String temp = "";
                    while ((temp = br.readLine()) != null) {

                        allText += temp;

                    }
                    br.close();
                    isr.close();
                    fis.close();
                    Toast.makeText(getBaseContext(), allText, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }

}