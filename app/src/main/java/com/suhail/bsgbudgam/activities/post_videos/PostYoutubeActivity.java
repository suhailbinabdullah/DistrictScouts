package com.suhail.bsgbudgam.activities.post_videos;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.albums.AlbumData;
import com.suhail.bsgbudgam.pojos.albums.AlbumModel;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.AppExtensions;

import java.util.List;

public class PostYoutubeActivity extends AppCompatActivity {
    private String TAG = PostYoutubeActivity.class.getSimpleName();

    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostYoutubeAdapter postYoutubeAdapter;
    private AlbumModel albumModel;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_youtube);
        context = PostYoutubeActivity.this;
        recyclerView = findViewById(R.id.recycler_view);
        textViewDescription = findViewById(R.id.text_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        albumModel = AppExtensions.getIntentObject(PostYoutubeActivity.this, savedInstanceState, AppConstants.KEY_CURRENT_OBJECT);

        if (albumModel != null) {
            getSupportActionBar().setTitle(albumModel.getAlbumName());
            List<AlbumData> albumData = albumModel.getAlbumData();
            if (albumModel.getAlbumDescription().equals("") || albumModel.getAlbumDescription() == null)
                textViewDescription.setVisibility(View.GONE);
            else
                textViewDescription.setText(albumModel.getAlbumDescription());
            if (albumModel.getAlbumData().size() == 0) {
                textViewDescription.setVisibility(View.VISIBLE);
                AppExtensions.showToast(context,"No Videos Here!");
                textViewDescription.setText("No Videos in this album");
            }

            postYoutubeAdapter = new PostYoutubeAdapter(context, albumData);
            recyclerView.setAdapter(postYoutubeAdapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
