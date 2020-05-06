package com.example.jkbsg.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jkbsg.R;
import com.example.jkbsg.activities.post_detail.PostDetail;
import com.example.jkbsg.utils.AppConstants;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = DashboardFragment.class.getSimpleName();

    private Context context;
    private Button introductionButton;
    private Button whatAreWe;
    private Button fundamentals;
    private Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = root.getContext();
        introductionButton = root.findViewById(R.id.intro_button);
        whatAreWe = root.findViewById(R.id.what_are_we);
        fundamentals = root.findViewById(R.id.fundamentals);

        introductionButton.setOnClickListener(this);
        whatAreWe.setOnClickListener(this);
        fundamentals.setOnClickListener(this);
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
                intent.putExtra(AppConstants.KEY_SOURCE_ACTIVITY, AppConstants.KEY_INTRODUCTION_BUTTON);
                startActivity(intent);
                break;
            case R.id.what_are_we:
                intent = new Intent(context, PostDetail.class);
                intent.putExtra(AppConstants.KEY_SOURCE_ACTIVITY, AppConstants.KEY_WHAT_ARE_WE_BUTTON);
                startActivity(intent);
                break;

            case R.id.fundamentals:
                intent = new Intent(context, PostDetail.class);
                intent.putExtra(AppConstants.KEY_SOURCE_ACTIVITY, AppConstants.KEY_FUNDAMENTALS);
                startActivity(intent);
                break;
        }
    }
}
