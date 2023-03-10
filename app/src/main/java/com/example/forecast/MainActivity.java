package com.example.forecast;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolBar;
    RadioButton rdo_grl,rdo_boy;
    ImageView img_View;
    String [] nameOfGirls={"رستا","الهه","نگار","کیانا","نازنین","ترانه","طناز","بهار","نازنین","سهیلا","آیدا","سوما","المیرا","دریا","سحر","نرگس","رویا","بِهناز","بنیتا","مریم","بهنوش"};
    String [] nameOfBoys ={"امیر","محمد","سعید","حامد","سامان","کیوان","کامران","حمید","علی","بابک","پوریا","هومن","مجید","افشین","رامین","امین","بهروز","سروش","امید","رضا","ساسان"};
    String [] nameOfCities={"بروجرد","اردبیله","تهرانه","خوزستانه","کردستانه","شیراز","همدان","سمنانه","گیلانه","ارومیه","ایلامه","اصفهان","قزوینه","زنجانه","تبریز","مراغه","آذربایجان غریه","یاسوجه","ساوه","ساری"};
    String res_cities_name , res_girls_names , res_boys_names;
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
               int id=menuItem.getItemId();
               switch(id){
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
        img_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int result = random.nextInt(nameOfGirls.length);
                int result_2 = random.nextInt(nameOfCities.length);
                Log.e("result",result+"");
                res_cities_name = nameOfCities[result_2];
                Toast.makeText(getApplicationContext(), res_cities_name+"", Toast.LENGTH_SHORT).show();
                if(rdo_boy.isChecked()){
                    res_girls_names = nameOfGirls[result];
                    Toast.makeText(getApplicationContext(), res_girls_names, Toast.LENGTH_SHORT).show();
                }
                else if(rdo_grl.isChecked()){
                    res_boys_names = nameOfBoys[result];
                    Toast.makeText(getApplicationContext(), res_boys_names, Toast.LENGTH_SHORT).show();
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
}