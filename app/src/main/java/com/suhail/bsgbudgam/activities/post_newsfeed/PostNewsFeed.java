package com.suhail.bsgbudgam.activities.post_newsfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsModel;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.suhail.bsgbudgam.utils.HtmlData;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class PostNewsFeed extends AppCompatActivity {

    private String TAG = PostNewsFeed.class.getSimpleName();
    private Context context;
    private WebView webView;
    private WebSettings webSettings;

    private static NewsModel currentPost;

    public static void setCurrentPost(NewsModel currentPost) {
        PostNewsFeed.currentPost = currentPost;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news_feed);
        context = PostNewsFeed.this;
        webView = findViewById(R.id.web_view);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //currentPost = AppExtensions.getIntentObject(PostNewsFeed.this, savedInstanceState, AppConstants.KEY_CURRENT_OBJECT);

        if (currentPost != null) {
            Objects.requireNonNull(getSupportActionBar()).setTitle(currentPost.getTitle());

            String timeStamp = AppExtensions.formatDate(currentPost.getTimeStamp()).toUpperCase();

            String headline = HtmlData.KEY_HEADLINE + currentPost.getTitle() + HtmlData.KEY_HEADLINE_AUTHOR + currentPost.getAuthor().toUpperCase() + HtmlData.KEY_HEADLINE_TIME_STAMP + timeStamp + HtmlData.KEY_HEADLINE_TERMINATOR;

            String fullPost = headline + currentPost.getBody();
            //webView.loadData(fullPost, "text/html; charset=UTF-8", null);

            String base64 = null;
            try {
                base64 = android.util.Base64.encodeToString(fullPost.getBytes("UTF-8"), android.util.Base64.DEFAULT);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            webView.loadData(base64, "text/html; charset=utf-8", "base64");
        } else {
            AppExtensions.showToast(context, "Can't load the data, please try later");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
