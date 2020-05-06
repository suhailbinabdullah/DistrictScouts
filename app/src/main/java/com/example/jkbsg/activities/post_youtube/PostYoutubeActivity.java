package com.example.jkbsg.activities.post_youtube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.youtube.YoutubeModel;
import com.example.jkbsg.pojos.youtube.YoutubeResults;
import com.example.jkbsg.ui.videos.VideosAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostYoutubeActivity extends AppCompatActivity {
    private String TAG=PostYoutubeActivity.class.getSimpleName();

    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostYoutubeAdapter postYoutubeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_youtube);
        context=PostYoutubeActivity.this;
        recyclerView=findViewById(R.id.recycler_view);
        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Album Example");
        }

        YoutubeResults youtubeResultsList = new YoutubeResults();

        List<YoutubeModel> youtubeModels = new ArrayList<>();

        YoutubeModel youtubeModel = new YoutubeModel();
        youtubeModel.setId("1");
        youtubeModel.setVideoId("<iframe width=\"100%\" height=\"240\"\n" +
                "src=\"https://www.youtube.com/embed/_obHwZNLpFA\">\n" +
                "</iframe>");
        youtubeModel.setYoutubeAlbumName("Album Example");

        YoutubeModel youtubeModel1 = new YoutubeModel();
        youtubeModel1.setVideoId("<iframe width=\"100%\" height=\"240\"\n" +
                "src=\"https://www.youtube.com/embed/gXVq0q_Babc\">\n" +
                "</iframe>");

        YoutubeModel youtubeModel2 = new YoutubeModel();
        youtubeModel2.setVideoId("<iframe width=\"100%\" height=\"240\"\n" +
                "src=\"https://www.youtube.com/embed/O4UijiSrYaw\">\n" +
                "</iframe>");

        youtubeModels.add(youtubeModel1);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel2);

        youtubeResultsList.setYoutubeModelResults(youtubeModels);


        postYoutubeAdapter = new PostYoutubeAdapter(context, youtubeResultsList);
        recyclerView.setAdapter(postYoutubeAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
