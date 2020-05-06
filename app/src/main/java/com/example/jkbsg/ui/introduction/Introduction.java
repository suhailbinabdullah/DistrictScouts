package com.example.jkbsg.ui.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jkbsg.R;
import com.example.jkbsg.utils.AppConstants;
import com.example.jkbsg.utils.HtmlData;


public class Introduction extends Fragment {
    private String TAG = Introduction.class.getSimpleName();

    private WebView webView;
    private String sourceButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_introduction, container, false);
        webView = v.findViewById(R.id.web_view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sourceButton = bundle.getString(AppConstants.KEY_SOURCE_ACTIVITY);

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
        return v;
    }
}
