package com.example.newshunt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton head, global, india, tech, crypto, business, sports, football, cric, enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head = findViewById(R.id.head);
        global = findViewById(R.id.globalhead);
        india = findViewById(R.id.indiahead);
        tech = findViewById(R.id.techhead);
        crypto = findViewById(R.id.cryptohead);
        business = findViewById(R.id.businesshead);
        sports = findViewById(R.id.sportshead);
        football = findViewById(R.id.footballhead);
        cric = findViewById(R.id.crickethead);
        enter = findViewById(R.id.enterhead);

        drawerLayout = findViewById(R.id.activity_main);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = new Intent(getApplicationContext(), Data.class);
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.headLines:
                        intent.putExtra("pass", "the-times-of-india");
                        startActivity(intent);
                        break;
                    case R.id.global:
                        intent.putExtra("pass", "google-news");
                        startActivity(intent);
                        break;
                    case R.id.india:
                        intent.putExtra("pass", "google-news-in");
                        startActivity(intent);
                        break;

                    case R.id.tech:
                        intent.putExtra("pass", "techradar");
                        startActivity(intent);
                        break;

                    case R.id.crypto:
                        intent.putExtra("pass", "crypto-coins-news");
                        startActivity(intent);
                        break;

                    case R.id.football:
                        intent.putExtra("pass", "football-italia");
                        startActivity(intent);
                        break;

                    case R.id.business:
                        intent.putExtra("pass", "business-insider");
                        startActivity(intent);
                        break;

                    case R.id.sports:
                        intent.putExtra("pass", "espn");
                        startActivity(intent);
                        break;

                    case R.id.cricket:
                        intent.putExtra("pass", "espn-cric-info");
                        startActivity(intent);
                        break;

                    case R.id.entertainment:
                        intent.putExtra("pass", "entertainment-weekly");
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });


        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "the-times-of-india");
                startActivity(intent1);
            }
        });

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "google-news-in");
                startActivity(intent1);
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "techradar");
                startActivity(intent1);
            }
        });

        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "google-news");
                startActivity(intent1);
            }
        });

        crypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "crypto-coins-news");
                startActivity(intent1);
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "business-insider");
                startActivity(intent1);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "espn");
                startActivity(intent1);
            }
        });

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "football-italia");
                startActivity(intent1);
            }
        });

        cric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "espn-cric-info");
                startActivity(intent1);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Data.class);
                intent1.putExtra("pass", "entertainment-weekly");
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
