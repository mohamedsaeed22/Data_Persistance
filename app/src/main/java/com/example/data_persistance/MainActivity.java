package com.example.data_persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_save, btn_restore;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = findViewById(R.id.btn_save);
        btn_restore = findViewById(R.id.btn_restore);

//        sp = PreferenceManager.getDefaultSharedPreferences(this); // لارجاع مؤشر على الملف العام للتطبيق

        sp = getSharedPreferences("email",MODE_PRIVATE);  //نفس الى فوق بس بعمل انا ملف

        editor = sp.edit();            //عشان اعدل علي البيانات

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name", "Mohamed");
                editor.commit();
                Toast.makeText(getBaseContext(), "data saved", Toast.LENGTH_SHORT).show();
            }
        });

        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sp.getString("name", "no Data");    //sp عشان اقرا البيانات
                Toast.makeText(getBaseContext(), "name=" + name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}