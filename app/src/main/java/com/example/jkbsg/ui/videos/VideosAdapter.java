package com.example.jkbsg.ui.videos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jkbsg.R;
import com.example.jkbsg.activities.post_youtube.PostYoutubeActivity;
import com.example.jkbsg.pojos.youtube.YoutubeModel;
import com.example.jkbsg.pojos.youtube.YoutubeResults;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private String TAG = VideosAdapter.class.getSimpleName();

    private Context context;
    private YoutubeResults youtubeResults;

    public VideosAdapter(Context context, YoutubeResults youtubeResultsList) {
        this.context = context;
        this.youtubeResults = youtubeResultsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_album_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YoutubeModel currentItem = youtubeResults.getYoutubeModelResults().get(position);
        holder.textView.setText(currentItem.getYoutubeAlbumName());

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostYoutubeActivity.class);
            intent.putExtra("AlbumName",currentItem.getYoutubeAlbumName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return youtubeResults.getYoutubeModelResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textViewSize;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            textViewSize = itemView.findViewById(R.id.size);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
