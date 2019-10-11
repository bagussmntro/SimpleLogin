package com.juaracoding.simplelogin.Ui;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.juaracoding.simplelogin.R;

public class WebApp extends AppCompatActivity {
    WebView webView;
    WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapps);

        webView = (WebView) findViewById(R.id.webView);
        /*webView.loadData("<html><p>Namanya <b>JUN</b></p></html>","text/html","UTF-8");*/
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://github.com/bagussmntro");
    }
}
