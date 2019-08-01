package com.example.ivanvelazquez.proyectointent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import butterknife.BindView;

import static com.example.ivanvelazquez.proyectointent.InfoActivity.LINK;

public class webActivity extends ButterBind {


    @BindView(R.id.webView)
    WebView web;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra(LINK);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl(url);


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_web;
    }


}
