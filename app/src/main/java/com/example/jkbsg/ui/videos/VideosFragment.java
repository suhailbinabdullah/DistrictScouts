package com.example.jkbsg.ui.videos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.albums.AlbumResults;
import com.example.jkbsg.repository.APIInterface;
import com.example.jkbsg.repository.APIService;
import com.example.jkbsg.utils.AppExtensions;
import com.github.ybq.android.spinkit.SpinKitView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {
    private static final String TAG = VideosFragment.class.getSimpleName();

    private Context context;
    private VideosAdapter videosAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpinKitView loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        loader = view.findViewById(R.id.loader);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        new GetVideoAlbums().execute();

        swipeRefreshLayout.setOnRefreshListener(() -> new GetVideoAlbums().execute());
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class GetVideoAlbums extends AsyncTask<String, Void, Void> {

        private AlbumResults youtubeResultsList = new AlbumResults();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... strings) {
            APIInterface apiInterface = APIService.getClient(context).create(APIInterface.class);
            Call<AlbumResults> callGetVideoAlbums = apiInterface.getVideoAlbumsAndData("V");
            callGetVideoAlbums.enqueue(new Callback<AlbumResults>() {
                @Override
                public void onResponse(Call<AlbumResults> call, Response<AlbumResults> response) {
                    if (response.code() == 200) {
                        youtubeResultsList = response.body();
                        videosAdapter = new VideosAdapter(context, youtubeResultsList);
                        recyclerView.setAdapter(videosAdapter);
                    } else AppExtensions.showToast(context, "Something went wrong, try later");
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<AlbumResults> call, Throwable t) {
                    AppExtensions.showToast(context, "Something went wrong, try later");
                    loader.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            return null;
        }
    }
}
