package com.example.jkbsg.activities.post_detail;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jkbsg.R;
import com.example.jkbsg.utils.AppConstants;
import com.example.jkbsg.utils.AppExtensions;
import com.example.jkbsg.utils.HtmlData;

public class PostDetail extends AppCompatActivity {
    private static String TAG = PostDetail.class.getSimpleName();

    private WebView webView;
    private String sourceButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail);
        webView = findViewById(R.id.web_view);

        sourceButton = AppExtensions.getIntentString(savedInstanceState, getIntent(), AppConstants.KEY_SOURCE_ACTIVITY);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(sourceButton);
        }

        switch (sourceButton) {
            case AppConstants.KEY_INTRODUCTION_BUTTON:
                webView.loadData(HtmlData.KEY_INTRODUCTION_STRING, "text/html; charset=UTF-8", null);
                break;
            case AppConstants.KEY_WHAT_ARE_WE_BUTTON:
                webView.loadData(HtmlData.KEY_WHAT_ARE_WE, "text/html; charset=UTF-8", null);
                break;
            case AppConstants.KEY_FUNDAMENTALS:
                webView.loadData(HtmlData.KEY_FUNDAMENTALS, "text/html; charset=UTF-8", null);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
