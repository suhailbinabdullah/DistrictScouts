package com.suhail.bsgbudgam.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsModel;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsResults;
import com.suhail.bsgbudgam.repository.APIInterface;
import com.suhail.bsgbudgam.repository.APIService;
import com.suhail.bsgbudgam.roomdb.ControlDAO;
import com.suhail.bsgbudgam.roomdb.ControlDB;
import com.suhail.bsgbudgam.roomdb.tables.NewsFeed;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.suhail.bsgbudgam.utils.ConnectivityHelper;
import com.suhail.bsgbudgam.utils.SharedPreferenceHelper;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    public String TAG = HomeFragment.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeAdapter homeAdapter;
    private SpinKitView loader;
    private ControlDAO controlDAO;
    private List<NewsFeed> offlineposts;
    private static int notificationFlag = 0;

    public static void setNotificationFlag(int notificationFlag) {
        HomeFragment.notificationFlag = notificationFlag;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = root.getContext();
        recyclerView = root.findViewById(R.id.recycler_view);
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
        loader = root.findViewById(R.id.loader);
        controlDAO = ControlDB.getInstance(getContext()).getControlDao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (ConnectivityHelper.isConnected(getContext())) {
                    new GetNewsFeedData().execute();
                    swipeRefreshLayout.setRefreshing(true);
                } else {
                    AppExtensions.showToast(getContext(), "Not connected to internet");
                    swipeRefreshLayout.setRefreshing(false);
                    loader.setVisibility(View.GONE);
                }
            }
        });

        AsyncTask.execute(this::showOfflineData);

        if (ConnectivityHelper.isConnected(getContext())) {
            if (notificationFlag == 1)
                SharedPreferenceHelper.setSharedPreferenceInt(context, AppConstants.KEY_IS_NEWSFEED_VISITED, 0);

            int isNewsFeedVisited = SharedPreferenceHelper.getSharedPreferenceInt(context, AppConstants.KEY_IS_NEWSFEED_VISITED);
            if (isNewsFeedVisited == 0) {
                new GetNewsFeedData().execute();
                SharedPreferenceHelper.setSharedPreferenceInt(context, AppConstants.KEY_IS_NEWSFEED_VISITED, 1);
            } else {
                loader.setVisibility(View.GONE);
            }
        } else {
            AppExtensions.showToast(getContext(), "Not connected to internet");
            swipeRefreshLayout.setRefreshing(false);
            loader.setVisibility(View.GONE);
        }
        return root;
    }

    private void showOfflineData() {

        offlineposts = controlDAO.getAllNewsFeedPosts();
        Log.i(TAG, "DATA_SHOWN");

        if (offlineposts != null) {
            List<NewsModel> result = new ArrayList<>();

            for (int i = 0; i < offlineposts.size(); i++) {
                NewsModel res = new NewsModel();
                res.setId(offlineposts.get(i).getId());
                res.setTitle(offlineposts.get(i).getTitle());
                res.setBody(offlineposts.get(i).getBody());
                res.setAuthor(offlineposts.get(i).getAuthor());
                res.setImageUrl(offlineposts.get(i).getImageUrl());
                res.setTimeStamp(offlineposts.get(i).getTimeStamp());
                result.add(res);
            }
            NewsResults responses = new NewsResults();
            responses.setResults(result);
            homeAdapter = new HomeAdapter(getActivity(), responses);

            requireActivity().runOnUiThread(() ->
                    recyclerView.setAdapter(homeAdapter));
        }

    }

    @SuppressLint("StaticFieldLeak")
    class GetNewsFeedData extends AsyncTask<String, Void, Void> {
        private NewsResults newsResults;

        @Override
        protected void onPreExecute() {
            loader.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            APIInterface apiInterface = APIService.getClient(context).create(APIInterface.class);
            Call<NewsResults> callNewsResults = apiInterface.getNewsFeedData();
            callNewsResults.enqueue(new Callback<NewsResults>() {
                @Override
                public void onResponse(Call<NewsResults> call, Response<NewsResults> response) {
                    if (response.code() == 200) {
                        newsResults = response.body();
                        homeAdapter = new HomeAdapter(context, newsResults);
                        recyclerView.setAdapter(homeAdapter);

                        List<NewsFeed> posts = new ArrayList<>();
                        for (int i = 0; i < newsResults.getResults().size(); i++) {
                            NewsFeed post = new NewsFeed();
                            post.setTitle(newsResults.getResults().get(i).getTitle());
                            post.setId(newsResults.getResults().get(i).getId());
                            post.setBody(newsResults.getResults().get(i).getBody());
                            post.setAuthor(newsResults.getResults().get(i).getAuthor());
                            post.setImageUrl(newsResults.getResults().get(i).getImageUrl());
                            post.setTimeStamp(newsResults.getResults().get(i).getTimeStamp());
                            posts.add(post);
                        }

                        AsyncTask.execute(() -> {
                            ControlDAO controlDAO = ControlDB.getInstance(context).getControlDao();
                            controlDAO.deleteAllOfflinePosts();
                        });

                        AppExtensions.insertNewsFeedDataToRoomDb(controlDAO, posts);
                    } else {
                        AppExtensions.showToast(context, "Something went wrong, please try later");
                    }
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<NewsResults> call, Throwable t) {
                    AppExtensions.showToast(context, "Something went wrong, please try later");
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            return null;
        }
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }*/
}
