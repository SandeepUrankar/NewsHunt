package com.example.newshunt;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Data extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    //ArrayList<String> articleImage = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    SQLiteDatabase articlesDb;
    ProgressBar progressBar;
    ListView listView;
    String title;
    TextView news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Intent intent = getIntent();
        title = intent.getStringExtra("pass");

        news = findViewById(R.id.textView);
        try {
            if (title.equals("the-times-of-india")) {
                news.setText(getResources().getString(R.string.head));
            } else if (title.equals("google-news")) {
                news.setText(getResources().getString(R.string.global));
            } else if (title.equals("google-news-in")) {
                news.setText(getResources().getString(R.string.india));
            } else if (title.equals("techradar")) {
                news.setText(getResources().getString(R.string.tech));
            } else if (title.equals("crypto-coins-news")) {
                news.setText(getResources().getString(R.string.crypto));
            } else if (title.equals("football-italia")) {
                news.setText(getResources().getString(R.string.football));
            } else if (title.equals("business-insider")) {
                news.setText(getResources().getString(R.string.business));
            } else if (title.equals("espn")) {
                news.setText(getResources().getString(R.string.sports));
            } else if (title.equals("espn-cric-info")) {
                news.setText(getResources().getString(R.string.cricket));
            } else if (title.equals("entertainment-weekly")) {
                news.setText(getResources().getString(R.string.enter));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar2);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), details.class);
                intent.putExtra("content", content.get(i));

                startActivity(intent);
            }
        });
        articlesDb = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articlesDb.execSQL("CREATE TABLE IF NOT EXISTS articles (title VARCHAR, content VARCHAR)");

        updateListView();


        DownloadTask task = new DownloadTask(this);
        try {
            if (title.equals("the-times-of-india")) {
                task.execute("https://newsapi.org/v2/top-headlines?sources=" + title + "&apiKey=d5a4a71ef810411d8c19043a4d39ba3d");
            } else {
                task.execute("https://newsapi.org/v2/everything?sources=" + title + "&apiKey=d5a4a71ef810411d8c19043a4d39ba3d");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        drawerLayout = findViewById(R.id.data);
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

                    case R.id.football:
                        intent.putExtra("pass", "football-italia");
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
    }


    public void updateListView() {

        Cursor cursor = articlesDb.rawQuery("SELECT * FROM articles", null);

        int contentIndex = cursor.getColumnIndex("content");
        int titleIndex = cursor.getColumnIndex("title");
        //int artDesc = cursor.getColumnIndex("descrp");

        if (cursor.moveToFirst()) {
            titles.clear();
            content.clear();
            //articleImage.clear();

            do {
                titles.add(cursor.getString(titleIndex));
                content.add(cursor.getString(contentIndex));
                //articleImage.add(cursor.getString(artDesc));
            } while (cursor.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        Data data;

        public DownloadTask(Data data) {
            this.data = data;
        }

        @Override
        protected void onPreExecute() {
            articlesDb.execSQL("DELETE FROM articles");
            data.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                Log.i("Result-r", result);
                JSONObject jsonObject = new JSONObject(result);

                String articleInfo = jsonObject.getString("articles");

                Log.i("Result-a", articleInfo);

                JSONArray jsonArray = jsonObject.optJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonPart = jsonArray.getJSONObject(i);

                    String articleTitle = jsonPart.optString("title");

                    String articleURL = jsonPart.optString("url");

                    String artDesc = jsonPart.optString("urlToImage");

                    Log.i("Result-t", articleTitle);
                    Log.i("Result-i", articleURL);
                    Log.i("Result-s", artDesc);

                    String sql = "INSERT INTO articles VALUES (?, ?)";

                    SQLiteStatement statement = articlesDb.compileStatement(sql);

                    statement.bindString(1, articleTitle);
                    statement.bindString(2, articleURL);
                    //statement.bindString(3, artDesc);

                    //statement.bindString(3, imageURL);


                    statement.execute();
    /*
                        titles.add(articleTitle);
                        content.add(articleURL);
                        arrayAdapter.notifyDataSetChanged();
    */
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
            data.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))

            return true;

        return super.onOptionsItemSelected(item);
    }
}