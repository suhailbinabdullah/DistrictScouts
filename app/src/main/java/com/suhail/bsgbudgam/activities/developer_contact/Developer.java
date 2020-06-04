package com.suhail.bsgbudgam.activities.developer_contact;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.suhail.bsgbudgam.utils.ConnectivityHelper;

public class Developer extends AppCompatActivity implements View.OnClickListener {

    private String TAG = Developer.class.getName();

    private String sourceActivity;
    private Context context;
    private TextView textViewVersionName, textViewDeveloperBody;

    private ImageView facebook, instagram, twitter, linkedin;

    private String facebookUrl = "https://www.facebook.com/suhailbinabdullah/";
    private String instagramUrl = "https://www.instagram.com/suhailbinabdullah/";
    private String twitterUrl = "https://twitter.com/biniabdullah";
    //    private String youtubeUrl = "https://www.youtube.com/channel/UCg7bPw012rLShlueoYjjoVA?view_as=subscriber";
    private String linkedinUrl = "https://www.linkedin.com/in/suhailbinabdullah/";
//    private String whatsappUrl = "https://wa.me/919419045557";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setContentView(R.layout.activity_developer);
        context = Developer.this;
        facebook = findViewById(R.id.imgFacebook);
        instagram = findViewById(R.id.imgInstagram);
        twitter = findViewById(R.id.imgTwitter);
        linkedin = findViewById(R.id.imgLinkedin);
        textViewVersionName = findViewById(R.id.text_view);
        textViewDeveloperBody = findViewById(R.id.text_view_developer_body);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("DEVELOPER");
        }


        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            textViewVersionName.setText("BSG Budgam Version : " + version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        twitter.setOnClickListener(this);
        linkedin.setOnClickListener(this);

        textViewDeveloperBody.setText(R.string.developer_body);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        if (ConnectivityHelper.isConnected(context)) {
            switch (view.getId()) {
                case R.id.imgFacebook:
                    AppExtensions.callIntent(context,facebookUrl);
                    break;
                case R.id.imgInstagram:
                    AppExtensions.callIntent(context,instagramUrl);
                    break;
                case R.id.imgTwitter:
                    AppExtensions.callIntent(context,twitterUrl);
                    break;
                case R.id.imgLinkedin:
                    AppExtensions.callIntent(context,linkedinUrl);
                    break;

            }
        } else AppExtensions.showToast(context, "Sorry, you are not connected to internet");
    }
}
