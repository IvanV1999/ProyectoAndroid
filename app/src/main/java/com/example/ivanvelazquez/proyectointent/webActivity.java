package com.example.ivanvelazquez.proyectointent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import static com.example.ivanvelazquez.proyectointent.InfoActivity.LINK;

public class webActivity extends AppCompatActivity {


    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url = getIntent().getStringExtra(LINK);
        web = findViewById(R.id.webView);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl(url);


    }


    public class WebAppInterface {
        Context context;

        WebAppInterface(Context c) {
            context = c;
        }

        public void showToast(String toast) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();


        }

    }


}
