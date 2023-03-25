package com.example.forecast;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class activity_Result extends AppCompatActivity {
    String  res_cities_name,res_name;
    String name_user, age;
    TextView txt_user,txt_Love,txt_finalRes,txt_timer;
    int time=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txt_user = findViewById(R.id.txt_user);
        txt_Love = findViewById(R.id.txt_Love);
        txt_finalRes = findViewById(R.id.txt_finalRes);
        txt_timer = findViewById(R.id.txt_timer);
        res_cities_name = getIntent().getStringExtra("res_cities_name");
        res_name = getIntent().getStringExtra("res_name");
        name_user = getIntent().getStringExtra("name");
        age = getIntent().getStringExtra("age");
        txt_user.setText(name_user);
        txt_Love.setText(res_name);
        txt_finalRes.setText("اهل "+res_cities_name+" به به بهم میاین چقدر...");
        timer();
    }
    private void timer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                time--;
                txt_timer.setText("0"+time);
                if(time>0){
                    timer();
                }
            }
        }, 1000);
    }
}