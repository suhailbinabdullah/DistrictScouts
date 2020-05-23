package com.example.jkbsg.activities.developer_contact;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jkbsg.R;
import com.example.jkbsg.utils.AppExtensions;

public class Contact extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = Contact.class.getSimpleName();

    private Context context;
    private LinearLayout linearLayout;
    private ImageView imgFacebook, imgDonate, imgWhatsapp;
    private TextView textViewVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        context = Contact.this;
        linearLayout = findViewById(R.id.linear_layout_account_details);
        imgFacebook = findViewById(R.id.imgFacebook);
        imgDonate = findViewById(R.id.imgDonate);
        imgWhatsapp = findViewById(R.id.imgWhatsapp);
        textViewVersion = findViewById(R.id.text_view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Contact & Donate");
        }

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            textViewVersion.setText("BSG Budgam Version : " + version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        imgFacebook.setOnClickListener(this);
        imgDonate.setOnClickListener(this);
        imgWhatsapp.setOnClickListener(this);
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgFacebook:
                AppExtensions.callIntent(context, "https://m.facebook.com/JK-Bharat-Scouts-and-Guides-Budgam-111903547186289/");
                break;
            case R.id.imgDonate:
                imgDonate.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.imgWhatsapp:
                AppExtensions.callIntent(context,"http://wa.me/919906444107");
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
