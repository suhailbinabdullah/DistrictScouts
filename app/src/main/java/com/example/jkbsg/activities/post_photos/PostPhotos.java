package com.example.jkbsg.activities.post_photos;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.albums.AlbumData;
import com.example.jkbsg.pojos.albums.AlbumModel;
import com.example.jkbsg.utils.AppConstants;
import com.example.jkbsg.utils.AppExtensions;

import java.util.List;

public class PostPhotos extends AppCompatActivity {
    private String TAG = PostPhotos.class.getSimpleName();

    private RecyclerView recyclerView;
    private Context context;
    private AlbumModel albumModel;
    private TextView textViewDescription;
    private PostPhotosAdapter postPhotosAdapter;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_photos);
        recyclerView = findViewById(R.id.recycler_view);
        textViewDescription = findViewById(R.id.text_view);
        context = PostPhotos.this;
        this.bundle=savedInstanceState;

        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (albumModel == null)
            albumModel = AppExtensions.getIntentObject(PostPhotos.this, savedInstanceState, AppConstants.KEY_CURRENT_OBJECT);

        if (albumModel != null) {
            getSupportActionBar().setTitle(albumModel.getAlbumName());
            List<AlbumData> albumData = albumModel.getAlbumData();
            if (albumModel.getAlbumDescription().equals("") || albumModel.getAlbumDescription() == null)
                textViewDescription.setVisibility(View.GONE);
            else
                textViewDescription.setText(albumModel.getAlbumDescription());
            if (albumModel.getAlbumData().size() == 0) {
                textViewDescription.setVisibility(View.VISIBLE);
                AppExtensions.showToast(context, "No Photos Here!");
                textViewDescription.setText("No Photos in this album");
            }

            postPhotosAdapter = new PostPhotosAdapter(context, albumData);
            recyclerView.setAdapter(postPhotosAdapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        finish();
        super.onConfigurationChanged(newConfig);
    }
}
