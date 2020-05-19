package com.example.jkbsg.activities.post_newsfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.newsfeed.NewsModel;
import com.example.jkbsg.utils.AppExtensions;
import com.example.jkbsg.utils.HtmlData;

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
            webView.loadData(fullPost, "text/html; charset=UTF-8", null);
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
