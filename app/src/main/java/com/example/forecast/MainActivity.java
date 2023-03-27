package com.example.forecast;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolBar;
    RadioButton rdo_grl,rdo_boy;
    ImageView img_View;
    EditText edt_name,edt_age;
    Button btn_mic_name,btn_mic_age;
    String [] nameOfGirls={"رستا","الهه","نگار","کیانا","نازنین","ترانه","طناز","بهار","نازنین زهرا","سهیلا","آیدا","سوما","المیرا","دریا","سحر","نرگس","رویا","نیلوفر","بنیتا","مریم","بهنوش"};
    String [] nameOfBoys ={"امیر","محمد","سعید","حامد","سامان","کیوان","کامران","حمید","علی","بابک","پوریا","هومن","مجید","افشین","رامین","امین","بهروز","سروش","امید","رضا","ساسان"};
    String [] nameOfCities={"بروجرد","اردبیله","تهرانه","خوزستانه","کردستانه","شیراز","همدان","سمنانه","گیلانه","ارومیه","ایلامه","اصفهان","قزوینه","زنجانه","تبریز","مراغه","آذربایجان غریه","یاسوجه","ساوه","ساری"};
    String res_cities_name , res_girls_names , res_boys_names,res_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation);
        rdo_grl = findViewById(R.id.rdo_grl);
        rdo_boy = findViewById(R.id.rdo_boy);
        img_View = findViewById(R.id.img_View);
        edt_age = findViewById(R.id.edt_age);
        edt_name = findViewById(R.id.edt_name);
        btn_mic_name = findViewById(R.id.btn_mic_name);
        btn_mic_age = findViewById(R.id.btn_mic_age);
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolBar,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_Us:
                        Toast.makeText(getApplicationContext(), "about us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help:
                        Toast.makeText(getApplicationContext(), "help", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact:
                        Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        img_View.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String name, age;
                name = edt_name.getText().toString().trim();
                age = edt_age.getText().toString().trim();
                if (TextUtils.isEmpty(name) || age.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill it up ", Toast.LENGTH_SHORT).show();
                } else {
                    Random random = new Random();
                    int result = random.nextInt(nameOfGirls.length);
                    int result_2 = random.nextInt(nameOfCities.length);
                    Log.e("result", result + "");
                    res_cities_name = nameOfCities[result_2];
                    // Toast.makeText(getApplicationContext(), res_cities_name + "", Toast.LENGTH_SHORT).show();
                    if (rdo_boy.isChecked()) {
                        res_girls_names = nameOfGirls[result];
                        res_name = res_girls_names;
                        // Toast.makeText(getApplicationContext(), res_girls_names, Toast.LENGTH_SHORT).show();
                    } else if (rdo_grl.isChecked()) {
                        res_boys_names = nameOfBoys[result];
                        res_name = res_boys_names;
                        //Toast.makeText(getApplicationContext(), res_boys_names, Toast.LENGTH_SHORT).show();
                    }
                }
                Vibrator vibrator = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);
                vibrator.vibrate(1500);
                Intent intent = new Intent(MainActivity.this, activity_Result.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("res_cities_name", res_cities_name);
                intent.putExtra("res_girls_names", res_girls_names);
                intent.putExtra("res_name", res_name);
                startActivity(intent);
                return false;
            }
        });
        btn_mic_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"fa");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"اسمت را بگویید؟");
                try{
                    startActivityForResult(intent,1);
                }catch(ActivityNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "دستگاه شما این قابلیت را ندارد!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_mic_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"fa");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"سن تان را بگویید!");
                try{
                    startActivityForResult(intent,2);
                }catch(ActivityNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "دستگاه شما این قابلیت را ندارد!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
               drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> res_list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edt_name.setText(res_list.get(0));
            }

        }else if(requestCode == 2){
           if(resultCode == RESULT_OK && data != null){
               ArrayList<String> res_list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
               edt_age.setText(res_list.get(0));
           }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}