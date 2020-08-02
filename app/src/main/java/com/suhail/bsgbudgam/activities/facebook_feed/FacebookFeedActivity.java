package com.suhail.bsgbudgam.activities.facebook_feed;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.facebook.FacebookFeedResult;
import com.suhail.bsgbudgam.repository.APIInterface;
import com.suhail.bsgbudgam.repository.APIService;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.suhail.bsgbudgam.utils.ConnectivityHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacebookFeedActivity extends AppCompatActivity {
    private String TAG = FacebookFeedActivity.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpinKitView loader;
    private TextView tvNoNews;
    private FacebookFeedAdapter facebookFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_feed);

        context = FacebookFeedActivity.this;
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        loader = findViewById(R.id.loader);
        tvNoNews = findViewById(R.id.no_news);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ConnectivityHelper.isConnected(context)) {
                    new GetFacebookFeed().execute();
                    swipeRefreshLayout.setRefreshing(true);
                } else {
                    AppExtensions.showToast(context, "Not connected to internet");
                    swipeRefreshLayout.setRefreshing(false);
                    loader.setVisibility(View.GONE);
                }
            }
        });

        if (ConnectivityHelper.isConnected(context)) {
            new GetFacebookFeed().execute();
        } else {
            AppExtensions.showToast(context, "Not connected to internet");
            swipeRefreshLayout.setRefreshing(false);
            loader.setVisibility(View.GONE);
        }
    }

    class GetFacebookFeed extends AsyncTask<String, Void, Void> {
        private FacebookFeedResult facebookFeedResult = new FacebookFeedResult();

        @Override
        protected void onPreExecute() {
            loader.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            APIInterface apiInterface = APIService.getClient(context).create(APIInterface.class);
            Call<FacebookFeedResult> callNewsResults = apiInterface.getFacebookFeed();
            callNewsResults.enqueue(new Callback<FacebookFeedResult>() {
                @Override
                public void onResponse(Call<FacebookFeedResult> call, Response<FacebookFeedResult> response) {
                    if (response.isSuccessful()) {
                        facebookFeedResult = response.body();
                        facebookFeedAdapter = new FacebookFeedAdapter(context, facebookFeedResult);
                    } else {
                        AppExtensions.showToast(context, "Something went wrong, please try later");
                        facebookFeedAdapter = new FacebookFeedAdapter(context, null);
                        tvNoNews.setVisibility(View.VISIBLE);
                    }
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setAdapter(facebookFeedAdapter);
                }

                @Override
                public void onFailure(Call<FacebookFeedResult> call, Throwable t) {
                    AppExtensions.showToast(context, "Something went wrong, please try later");
                    facebookFeedAdapter = new FacebookFeedAdapter(context, null);
                    tvNoNews.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            return null;
        }
    }
}