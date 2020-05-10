package com.example.jkbsg.activities.post_videos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.albums.AlbumData;

import java.util.List;

public class PostYoutubeAdapter extends RecyclerView.Adapter<PostYoutubeAdapter.ViewHolder> {
    private String TAG = PostYoutubeAdapter.class.getSimpleName();

    private Context context;
    private List<AlbumData> albumData;

    public PostYoutubeAdapter(Context context, List<AlbumData> albumData) {
        this.context = context;
        this.albumData = albumData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_view_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumData currentItem = albumData.get(position);
        //holder.webView.loadData(currentItem.getVideoId(), "text/html; charset=UTF-8", null);
        holder.webView.loadData("<iframe width=\"100%\" height=\"240\"\n" +
                "src=\"https://www.youtube.com/embed/" + currentItem.getDataUrl() + "\">\n" +
                "</iframe>", "text/html; charset=UTF-8", null);
    }

    @Override
    public int getItemCount() {
        return albumData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private WebView webView;
        private WebSettings webSettings;

        @SuppressLint("SetJavaScriptEnabled")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.web_view);
            webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            //webSettings.setLoadWithOverviewMode(true);
            //webSettings.setUseWideViewPort(true);
        }
    }
}
