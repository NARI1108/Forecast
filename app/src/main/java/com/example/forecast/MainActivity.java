package com.example.forecast;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigation = findViewById(R.id.navigation);
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