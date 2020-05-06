package com.example.jkbsg.ui.videos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.youtube.YoutubeModel;
import com.example.jkbsg.pojos.youtube.YoutubeResults;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {
    private static final String TAG = VideosFragment.class.getSimpleName();

    private Context context;
    private VideosAdapter videosAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        YoutubeResults youtubeResultsList = new YoutubeResults();

        List<YoutubeModel> youtubeModels = new ArrayList<>();

        YoutubeModel youtubeModel = new YoutubeModel();

        youtubeModel.setId("1");
        youtubeModel.setVideoId("<iframe width=\"420\" height=\"315\"\n" +
                "src=\"https://www.youtube.com/embed/tgbNymZ7vqY\">\n" +
                "</iframe>");
        youtubeModel.setYoutubeAlbumName("Album Example");
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);
        youtubeModels.add(youtubeModel);

        youtubeResultsList.setYoutubeModelResults(youtubeModels);


        videosAdapter = new VideosAdapter(context, youtubeResultsList);
        recyclerView.setAdapter(videosAdapter);


        return view;
    }
}
