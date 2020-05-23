package com.example.jkbsg.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.jkbsg.roomdb.ControlDAO;
import com.example.jkbsg.roomdb.tables.NewsFeed;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class AppExtensions {

    public static String loadJSONFromAsset(Context context, String assetName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(assetName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //get string from intent
    public static String getIntentString(Bundle savedInstanceState, Intent intent, String key) {
        String string;
        if (savedInstanceState == null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                string = null;
            } else {
                string = extras.getString(key);
            }
        } else {
            string = (String) savedInstanceState.getSerializable(key);
        }
        return string;
    }


    public static <T> T getIntentObject(Activity activity, Bundle savedInstance, String key) {
        T object = null;
        if (savedInstance == null) {
            Bundle extras = activity.getIntent().getExtras();
            if (extras == null) {
                object = null;
            } else {
                object = (T) activity.getIntent().getSerializableExtra(key);
            }
        }
        return object;
    }


    //hide Keyboard
    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                imm.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //to validate components
    public static Boolean isValid(String componentName, TextInputEditText componentId) {

        String str = componentId.getText().toString();

        if (!TextUtils.isEmpty(str) && componentId.getText().length() != 0) {
            //success
            return true;
        } else {
            componentId.setError(componentName + " is Mandatory");
            return false;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void openUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd' 'kk:mm:ss");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
        // representation according to the given format, but still in UTC
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        // Convert UTC into Local time
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    public static void insertNewsFeedDataToRoomDb(final ControlDAO controlDAO, final List<NewsFeed> posts) {
        try {

            if (controlDAO != null) {
                if (posts != null) {
                    AsyncTask.execute(() -> {
                        controlDAO.insertNewsfeedData(posts);
                        Log.i("mInsertPostsToRoomDB", "DATA INSERTED TO ROOMDB");
                    });
                }
            } else {
                Log.i("mInsertPostsToRoomDB", "DATABASE NOT INITIALIZED");

            }
        } catch (Exception ex) {
            Log.e("mInsertPostsToRoomDB", "error" + ex);
        }
    }

    public static void callIntent(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
