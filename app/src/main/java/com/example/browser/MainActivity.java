package com.example.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText inputURL;
    Button send;
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = findViewById(R.id.button);
        web = (WebView) findViewById(R.id.WebView);
        inputURL = (EditText) findViewById(R.id.autoCompleteTextView);
        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);

        web.loadUrl("https://www.google.com/");
        web.setWebViewClient(new WebViewClient());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = inputURL.getText().toString();

                if(!url.startsWith("http://")){
                    url = "http://";
                }
                web.loadUrl(url);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(web.getWindowToken(), 0);
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(web.canGoBack())
            web.goBack();
        else
            super.onBackPressed();
    }
}