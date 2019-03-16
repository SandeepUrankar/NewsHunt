package com.example.newshunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class details extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        url = intent.getStringExtra("content");
        progressBar = findViewById(R.id.loader);
        webView = findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public boolean onSupportNavigateUp() {
//        finish();
        Intent intent = new Intent(getApplicationContext(), Data.class);
        startActivity(intent);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.shareButton) {

            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
            ShareIntent.setType("text/plain");
            ShareIntent.putExtra(Intent.EXTRA_SUBJECT, "URL");
            ShareIntent.putExtra(Intent.EXTRA_TEXT, url);
            // value which you want to share
            startActivity(Intent.createChooser(ShareIntent, "Share Using.."));

        }
        return super.onOptionsItemSelected(item);
    }
}
