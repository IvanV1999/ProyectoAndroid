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
        web.addJavascriptInterface(new WebAppInterface(this), "Android");
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.setWebChromeClient(new WebChromeClient());
        //web.loadUrl(url);
        web.loadUrl(" < Button type = \"button\" value =\"Say hello\" onClick = \"showAndroidToast('Hello Android!')\"/> <script type = \"text/javascript\"> function showAndroidToast(toast) Android.showToast(toast); } </script >");

    }


    public class WebAppInterface {
        Context context;

        WebAppInterface(Context c) {
            context = c;
        }


        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();


        }

    }


}
