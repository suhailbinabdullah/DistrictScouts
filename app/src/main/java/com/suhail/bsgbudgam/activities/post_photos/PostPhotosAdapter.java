package com.suhail.bsgbudgam.activities.post_photos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.suhail.bsgbudgam.R;
import com.suhail.bsgbudgam.pojos.albums.AlbumData;
import com.suhail.bsgbudgam.utils.AppExtensions;

import java.util.List;

public class PostPhotosAdapter extends RecyclerView.Adapter<PostPhotosAdapter.ViewHolder> {
    private String TAG = PostPhotosAdapter.class.getSimpleName();

    private Context context;
    private List<AlbumData> albumData;

    public PostPhotosAdapter(Context context, List<AlbumData> albumData) {
        this.context = context;
        this.albumData = albumData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_photos_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumData currentItem = albumData.get(position);
        if (currentItem.getDataUrl() == null) {
            holder.photo.setVisibility(View.GONE);
        } else {
            holder.photo.setVisibility(View.VISIBLE);
            // Load thumbnail with glide
            Glide.with(context.getApplicationContext())
                    .load(currentItem.getDataUrl())
                    .into(holder.photo);
        }

        holder.photo.setOnClickListener(view -> {
            try{
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setDataAndType(Uri.parse("file:///mnt/sdcard/image/Apples.jpg"),"image/*");
                intent.setDataAndType(Uri.parse(currentItem.getDataUrl()), "image/*");
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                AppExtensions.showToast(context," Sorry, Cant open this photo");
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;

        @SuppressLint("SetJavaScriptEnabled")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.image_view);
        }
    }
}
