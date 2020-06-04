package com.suhail.bsgbudgam.ui.photos;

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

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.albums.AlbumResults;
import com.suhail.bsgbudgam.repository.APIInterface;
import com.suhail.bsgbudgam.repository.APIService;
import com.suhail.bsgbudgam.ui.videos.VideosFragment;
import com.suhail.bsgbudgam.utils.AppExtensions;
import com.github.ybq.android.spinkit.SpinKitView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {
    private static final String TAG = VideosFragment.class.getSimpleName();

    private Context context;
    private PhotosAdapter photosAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpinKitView loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        loader = view.findViewById(R.id.loader);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        new GetPhotoAlbums().execute();

        swipeRefreshLayout.setOnRefreshListener(() -> new GetPhotoAlbums().execute());
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class GetPhotoAlbums extends AsyncTask<String, Void, Void> {

        private AlbumResults photoResultsList = new AlbumResults();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... strings) {
            APIInterface apiInterface = APIService.getClient(context).create(APIInterface.class);
            Call<AlbumResults> callGetVideoAlbums = apiInterface.getPhotoAlbumsAndData("P");
            callGetVideoAlbums.enqueue(new Callback<AlbumResults>() {
                @Override
                public void onResponse(Call<AlbumResults> call, Response<AlbumResults> response) {
                    if (response.code() == 200) {
                        photoResultsList = response.body();
                        photosAdapter = new PhotosAdapter(context, photoResultsList);
                        recyclerView.setAdapter(photosAdapter);
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
