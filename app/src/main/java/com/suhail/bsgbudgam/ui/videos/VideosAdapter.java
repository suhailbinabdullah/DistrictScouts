package com.suhail.bsgbudgam.ui.videos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.activities.post_videos.PostYoutubeActivity;
import com.suhail.bsgbudgam.pojos.albums.AlbumModel;
import com.suhail.bsgbudgam.pojos.albums.AlbumResults;
import com.suhail.bsgbudgam.utils.AppConstants;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private String TAG = VideosAdapter.class.getSimpleName();

    private Context context;
    private AlbumResults youtubeResults;

    public VideosAdapter(Context context, AlbumResults youtubeResultsList) {
        this.context = context;
        this.youtubeResults = youtubeResultsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_album_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumModel currentItem = youtubeResults.getResults().get(position);
        holder.textView.setText(currentItem.getAlbumName());
        holder.textViewSize.setText(currentItem.getAlbumData().size() + " Videos");


        if (currentItem.getAlbumImageUrl() == null) {
            holder.albumImage.setVisibility(View.GONE);
        } else {
            holder.albumImage.setVisibility(View.VISIBLE);
            // Load thumbnail with glide
            Glide.with(context.getApplicationContext())
                    .load(currentItem.getAlbumImageUrl())
                    .into(holder.albumImage);
        }


        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostYoutubeActivity.class);
            intent.putExtra(AppConstants.KEY_CURRENT_OBJECT, currentItem);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return youtubeResults.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textViewSize;
        private CardView cardView;
        private ImageView albumImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            textViewSize = itemView.findViewById(R.id.size);
            cardView = itemView.findViewById(R.id.card_view);
            albumImage = itemView.findViewById(R.id.album_image);
        }
    }
}
