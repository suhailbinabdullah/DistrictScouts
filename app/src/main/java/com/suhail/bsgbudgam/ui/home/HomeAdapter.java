package com.suhail.bsgbudgam.ui.home;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
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
import com.suhail.bsgbudgam.activities.post_newsfeed.PostNewsFeed;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsModel;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsResults;
import com.suhail.bsgbudgam.utils.AppConstants;
import com.suhail.bsgbudgam.utils.AppExtensions;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private NewsResults newsResults;

    public HomeAdapter(Context context, NewsResults newsResults) {
        this.context = context;
        this.newsResults = newsResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsModel currentItem = newsResults.getResults().get(position);
        holder.title.setText(currentItem.getTitle());
        holder.author.setText(currentItem.getAuthor());

        String date = AppExtensions.formatDate(currentItem.getTimeStamp());
        holder.timeStamp.setText(date);
        String trailTextHTML = currentItem.getBody();
        holder.body.setText(Html.fromHtml(Html.fromHtml(trailTextHTML).toString()));

        if (currentItem.getImageUrl() == null) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            // Load thumbnail with glide
            Glide.with(context.getApplicationContext())
                    .load(currentItem.getImageUrl())
                    .into(holder.imageView);
        }

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostNewsFeed.class);
            intent.putExtra(AppConstants.KEY_CURRENT_OBJECT, currentItem);
            PostNewsFeed.setCurrentPost(currentItem);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return newsResults.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, body, author, timeStamp;
        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            author = itemView.findViewById(R.id.author);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            imageView = itemView.findViewById(R.id.thumbnail_image_card);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
