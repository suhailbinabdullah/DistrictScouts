package com.suhail.bsgbudgam.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.activities.developer_contact.Contact;
import com.suhail.bsgbudgam.activities.developer_contact.Developer;
import com.suhail.bsgbudgam.activities.facebook_feed.FacebookFeedActivity;
import com.suhail.bsgbudgam.activities.post_detail.PostDetail;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.suhail.bsgbudgam.utils.ConnectivityHelper;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //    private AdView adView;
    public static NavController navController;
    private String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        BottomNavigationView navView = findViewById(R.id.nav_view_bottom_bar);
//        adView = findViewById(R.id.adView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_videos_library, R.id.navigation_photo_library)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //**********************************************************************************************************

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        /*MobileAds.initialize(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/


        //Give the TabLayout the ViewPager
        //TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        //tabLayout.setupWithViewPager(viewPager);
        //Set gravity for tab bar
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        navigationView = findViewById(R.id.nav_view_drawer);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        // Set the default fragment when starting the app
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = Objects.requireNonNull(task.getResult()).getToken();

                        // Log and toast
                        Log.e(TAG, token);
                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic("global")
                .addOnCompleteListener(task -> {
                    String msg = "Subscribed";
                    if (!task.isSuccessful()) {
                        msg = "Un-Subscribed";
                    }
                    Log.e(TAG, msg);
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer

        switch (id) {
            case R.id.nav_home:
                navController.navigate(R.id.navigation_home);
                break;
            case R.id.nav_dashboard:
                navController.navigate(R.id.navigation_dashboard);
                break;
            case R.id.nav_videos:
                navController.navigate(R.id.navigation_videos_library);
                break;
            case R.id.nav_photos:
                navController.navigate(R.id.navigation_photo_library);
                break;
            case R.id.nav_budgam_scouts:
                startActivity(new Intent(context, Developer.class));
                break;
            case R.id.nav_login:
                if (ConnectivityHelper.isConnected(context)) {
                    Intent intent = new Intent(context, PostDetail.class);
                    PostDetail.setSourceButton(AppConstants.KEY_CREATE_NEW_POST);
                    startActivity(intent);
                } else {
                    AppExtensions.showToast(context, "You are not connected to Internet");
                }
                //startActivity(new Intent(context, FacebookFeedActivity.class));
                break;
            case R.id.nav_contacts:
                startActivity(new Intent(context, Contact.class));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    // Initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            /*Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);*/
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
