package com.example.jkbsg.activities.post_youtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.youtube.YoutubeModel;
import com.example.jkbsg.pojos.youtube.YoutubeResults;

public class PostYoutubeAdapter extends RecyclerView.Adapter<PostYoutubeAdapter.ViewHolder> {
    private String TAG = PostYoutubeAdapter.class.getSimpleName();

    private Context context;
    private YoutubeResults youtubeResults;

    public PostYoutubeAdapter(Context context, YoutubeResults youtubeResultsList) {
        this.context = context;
        this.youtubeResults = youtubeResultsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_view_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YoutubeModel currentItem = youtubeResults.getYoutubeModelResults().get(position);
        WebSettings webSettings = holder.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        holder.webView.loadData(currentItem.getVideoId(), "text/html; charset=UTF-8", null);
    }

    @Override
    public int getItemCount() {
        return youtubeResults.getYoutubeModelResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.web_view);
        }
    }
}
