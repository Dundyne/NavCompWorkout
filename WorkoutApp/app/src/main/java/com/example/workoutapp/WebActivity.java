package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.matprat.no/oppskrifter/familien/p/pytt-i-panne-med-lammeskav/");
    }
}