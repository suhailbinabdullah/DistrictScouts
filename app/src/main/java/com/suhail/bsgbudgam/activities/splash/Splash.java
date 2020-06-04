package com.suhail.bsgbudgam.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.activities.main.MainActivity;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.SharedPreferenceHelper;

public class Splash extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = Splash.this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferenceHelper.setSharedPreferenceInt(context, AppConstants.KEY_IS_NEWSFEED_VISITED,0);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        }, 2700);
    }
}
