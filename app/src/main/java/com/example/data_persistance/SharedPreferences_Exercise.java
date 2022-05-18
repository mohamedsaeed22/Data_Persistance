package com.example.data_persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferences_Exercise extends AppCompatActivity {


    //تطبيق لو خرجت منه يخزن الداتا فى ال preferences  حتى لو نسيت اضغط على save

    EditText et_username, et_pass, et_age;
    Button btn_save, btn_getData;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public String USER_KEY = "USER_NAME";
    public String PASS_KEY = "PASSWORD";
    public String AGE_KEY = "AGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_exercise);

        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_pass);
        et_age = findViewById(R.id.et_age);
        btn_save = findViewById(R.id.btn_savedata);
        btn_getData = findViewById(R.id.btn_getdata);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();

            }
        });

        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sp.getString(USER_KEY, "no Value");
                String password = sp.getString(PASS_KEY, "no Value");
                String age = sp.getString(AGE_KEY, "no Value");

                Toast.makeText(getBaseContext(), username + "\n" + password + "\n" + age + "\n", Toast.LENGTH_LONG).show();

            }
        });


    }

    public void SaveData() {

        String username = et_username.getText().toString();
        String pass = et_pass.getText().toString();
        String age = et_age.getText().toString();

        editor.putString(USER_KEY, username);
        editor.putString(PASS_KEY, pass);
        editor.putString(AGE_KEY, age);
        editor.commit();

        et_username.setText("");
        et_pass.setText("");
        et_age.setText("");

        Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveData();
    }
}









