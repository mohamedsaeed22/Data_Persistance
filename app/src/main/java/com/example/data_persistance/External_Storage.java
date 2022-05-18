package com.example.data_persistance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class External_Storage extends AppCompatActivity {

    EditText et_username, et_pass, et_age;
    Button btn_save, btn_getData;
    public static final String FILE_NAME = "USER_INFO";
    public static final int REQUEST_CODE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_pass);
        et_age = findViewById(R.id.et_age);
        btn_save = findViewById(R.id.btn_savedata);
        btn_getData = findViewById(R.id.btn_getdata);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            String [] permissions={ Manifest.permission.WRITE_EXTERNAL_STORAGE};

            ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE);
        }else {

        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_username.getText().toString();
                String pass = et_pass.getText().toString();
                String age = et_age.getText().toString();

                if (isStorageAvailable()){
                    File ex_Storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                    File f = new File(ex_Storage,FILE_NAME);
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }

    public boolean isStorageAvailable() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getBaseContext(), "permissions granted", Toast.LENGTH_SHORT).show();

                }
                return;

        }

    }
}