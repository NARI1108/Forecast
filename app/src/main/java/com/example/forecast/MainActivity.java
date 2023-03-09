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
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolBar;
    String [] nameOfGirls={"رستا","الهه","نگار","کیانا","نازنین","ترانه","طناز","بهار","نازنین","سهیلا","آیدا","سوما","المیرا","دریا","سحر","نرگس","رویا","بِهناز","بنیتا","مریم","بهنوش"};
    String [] nameOfBoys ={"امیر","محمد","سعید","حامد","سامان","کیوان","کامران","حمید","علی","بابک","پوریا","هومن","مجید","افشین","رامین","امین","بهروز","سروش","امید","رضا","ساسان"};
    String [] nameOfCities={"بروجرد","اردبیله","تهرانه","خوزستانه","کردستانه","شیراز","همدان","سمنانه","گیلانه","ارومیه","ایلامه","اصفهان","قزوینه","زنجانه","تبریز","مراغه","آذربایجان غریه","یاسوجه","ساوه","ساری"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation);
        Random random = new Random();
        int result = random.nextInt(nameOfGirls.length);
        Toast.makeText(getApplicationContext(), nameOfGirls[result], Toast.LENGTH_SHORT).show();
        Log.e("result",result+"");
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