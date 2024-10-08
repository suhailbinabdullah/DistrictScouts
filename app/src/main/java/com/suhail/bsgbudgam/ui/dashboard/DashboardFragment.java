package com.suhail.bsgbudgam.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.activities.post_detail.PostDetail;
import com.suhail.bsgbudgam.utils.AppConstants;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = DashboardFragment.class.getSimpleName();

    private Context context;
    private Button introductionButton;
    private Button whatAreWe;
    private Button fundamentals;
    private Button prayerFlagSong;
    private Button lawPromise;
    private Button distBody;
    private Button distAims;
    private Button scoutingInJk;
    private Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = root.getContext();
        introductionButton = root.findViewById(R.id.intro_button);
        whatAreWe = root.findViewById(R.id.what_are_we);
        fundamentals = root.findViewById(R.id.fundamentals);
        prayerFlagSong = root.findViewById(R.id.prayer_flag_song);
        lawPromise = root.findViewById(R.id.law_promise);
        distBody = root.findViewById(R.id.district_body);
        distAims = root.findViewById(R.id.district_aims);
        scoutingInJk = root.findViewById(R.id.scouting_in_jk);

        introductionButton.setOnClickListener(this);
        whatAreWe.setOnClickListener(this);
        fundamentals.setOnClickListener(this);
        prayerFlagSong.setOnClickListener(this);
        lawPromise.setOnClickListener(this);
        distBody.setOnClickListener(this);
        distAims.setOnClickListener(this);
        scoutingInJk.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intro_button:
                /*bundle.putString(AppConstants.KEY_SOURCE_ACTIVITY, AppConstants.KEY_INTRODUCTION_BUTTON);
                fragment.setArguments(bundle);
                MainActivity.navController.navigate(R.id.navigation_introduction, bundle);*/
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_INTRODUCTION_BUTTON);
                startActivity(intent);
                break;
            case R.id.what_are_we:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_WHAT_ARE_WE_BUTTON);
                startActivity(intent);
                break;

            case R.id.fundamentals:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_FUNDAMENTALS);
                startActivity(intent);
                break;

            case R.id.prayer_flag_song:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_PRAYER_FLAG_SONG);
                startActivity(intent);
                break;

            case R.id.law_promise:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_LAW_PROMISE);
                startActivity(intent);
                break;

            case R.id.district_body:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_DIST_BODY);
                startActivity(intent);
                break;

            case R.id.district_aims:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_DIST_AIMS);
                startActivity(intent);
                break;

            case R.id.scouting_in_jk:
                intent = new Intent(context, PostDetail.class);
                PostDetail.setSourceButton(AppConstants.KEY_SCOUTING_IN_JK);
                startActivity(intent);
                break;
        }
    }
}
