package com.example.jkbsg.activities.post_detail;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
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
    private WebSettings webSettings;
    int flag=0;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        webView = findViewById(R.id.web_view);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }

        if (sourceButton == null && flag==0) {
            sourceButton = AppExtensions.getIntentString(savedInstanceState, getIntent(), AppConstants.KEY_SOURCE_ACTIVITY);
            flag=1;

        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(sourceButton);
        }

        if (sourceButton != null) {
            switch (sourceButton) {
                case AppConstants.KEY_INTRODUCTION_BUTTON:
                    webView.loadData(HtmlData.KEY_INTRODUCTION_STRING, "text/html; charset=UTF-8", null);
                    break;
                case AppConstants.KEY_WHAT_ARE_WE_BUTTON:
                    webView.loadData(HtmlData.KEY_WHAT_ARE_WE, "text/html; charset=UTF-8", null);
                    break;
                case AppConstants.KEY_FUNDAMENTALS:
                    webView.loadData(HtmlData.KEY_FUNDAMENTALS, "text/html; charset=UTF-8", null);
                    break;
                case AppConstants.KEY_PRAYER_FLAG_SONG:
                    webView.loadData(HtmlData.KEY_PRAYER_SONG, "text/html; charset=UTF-8", null);
                    break;

                case AppConstants.KEY_LAW_PROMISE:
                    webView.loadData(HtmlData.KEY_LAW_PROMISE, "text/html; charset=UTF-8", null);
                    break;

                case AppConstants.KEY_DIST_BODY:
                    webView.clearCache(true);
                    webView.loadUrl("https://soibugh.web.app/dist_body.html");
                    break;

                case AppConstants.KEY_DIST_AIMS:
                    //webView.loadUrl("https://soibugh.web.app/aims_of_dist.html");
                    webView.loadData(HtmlData.KEY_DISTRICT_AIMS, "text/html; charset=UTF-8", null);
                    break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
