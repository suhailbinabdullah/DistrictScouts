package com.example.jkbsg.ui.photos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jkbsg.R;

public class PhotosFragment extends Fragment {
    WebView videoView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_photos, container, false);

        videoView = root.findViewById(R.id.video);
        videoView.setVisibility(View.VISIBLE);
        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String s="<iframe width=\"100%\" height=\"240\"\n" +
                "src=\"https://www.youtube.com/embed/_obHwZNLpFA\">\n" +
                "</iframe>";
        videoView.loadData(s,"text/html; charset=UTF-8", null);
        return root;
    }
}
