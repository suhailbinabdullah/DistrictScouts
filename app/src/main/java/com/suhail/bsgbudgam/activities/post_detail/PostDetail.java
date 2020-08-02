package com.suhail.bsgbudgam.activities.post_detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.activities.facebook_feed.FacebookFeedActivity;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.AppExtensions;

public class PostDetail extends AppCompatActivity {
    private static String TAG = PostDetail.class.getSimpleName();

    private WebView webView;
    private static String sourceButton;
    private WebSettings webSettings;
    private RelativeLayout relativeLayoutMain;
    private Context context;

    public static void setSourceButton(String sourceButton) {
        PostDetail.sourceButton = sourceButton;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        webView = findViewById(R.id.web_view);
        relativeLayoutMain = findViewById(R.id.relative_layout);
        context = PostDetail.this;

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }

        /*if (sourceButton == null) {
            sourceButton = AppExtensions.getIntentString(savedInstanceState, getIntent(), AppConstants.KEY_SOURCE_ACTIVITY);
        }*/
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(sourceButton);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                switch (url) {
                    case "https://thesuhail.live/index.php?post=success":
                        AppExtensions.showToast(context, "Post created Successfully");
                        finish();
                        break;
                    case "https://thesuhail.live/index.php?clearSession=true":
                        AppExtensions.showToast(context, "You have been logged off");
                        finish();
                        break;
                    case "https://thesuhail.live/login.php":
                    case "https://thesuhail.live/loginStudents.php":
                        AppExtensions.showToast(context, "You are not allowed to view other client's content");
                        finish();
                        break;
                    case "https://thesuhail.live/index.php":
                        AppExtensions.showToast(context, "Your account may get blocked if you continue do random clicks");
                        finish();
                        break;

                    case "https://www.facebook.com/JK-Bharat-Scouts-and-Guides-Budgam-111903547186289/":
                        finish();
                        startActivity(new Intent(context, FacebookFeedActivity.class));
                        break;
                }
                return false;
            }
        });

        if (sourceButton != null) {
            switch (sourceButton) {
                case AppConstants.KEY_INTRODUCTION_BUTTON:
                    webView.loadUrl("file:///android_asset/introduction.html");
                    break;
                case AppConstants.KEY_WHAT_ARE_WE_BUTTON:
                    webView.loadUrl("file:///android_asset/whatAreWe.html");
                    break;
                case AppConstants.KEY_FUNDAMENTALS:
                    webView.loadUrl("file:///android_asset/fundamentals.html");
                    break;
                case AppConstants.KEY_PRAYER_FLAG_SONG:
                    webView.loadUrl("file:///android_asset/prayer.html");
                    break;

                case AppConstants.KEY_LAW_PROMISE:
                    webView.loadUrl("file:///android_asset/lawPromise.html");
                    break;

                case AppConstants.KEY_DIST_BODY:
                    webView.clearCache(true);
                    webView.loadUrl("https://soibugh.web.app/dist_body.html");
                    break;

                case AppConstants.KEY_DIST_AIMS:
                    //webView.loadUrl("https://soibugh.web.app/aims_of_dist.html");
                    webView.loadUrl("file:///android_asset/distAimsAndObjec.html");
                    break;

                case AppConstants.KEY_SCOUTING_IN_JK:
                    webView.loadUrl("file:///android_asset/scInJk.html");
                    break;

                case AppConstants.KEY_CREATE_NEW_POST:
                    webView.loadUrl("https://thesuhail.live/newScouts.php");
                    relativeLayoutMain.setPadding(0, 0, 0, 0);
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
