package com.suhail.bsgbudgam.activities.facebook_feed;

import android.content.Context;
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
import com.suhail.bsgbudgam.pojos.facebook.Datum;
import com.suhail.bsgbudgam.pojos.facebook.FacebookFeedResult;
import com.suhail.bsgbudgam.utils.AppExtensions;

public class FacebookFeedAdapter extends RecyclerView.Adapter<FacebookFeedAdapter.ViewHolder> {
    private Context context;
    private FacebookFeedResult facebookFeedResult;

    public FacebookFeedAdapter(Context context, FacebookFeedResult facebookFeedResult) {
        this.context = context;
        this.facebookFeedResult = facebookFeedResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_feed_list, parent, false);
        return new FacebookFeedAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (facebookFeedResult != null) {
            Datum currentItem = facebookFeedResult.getData().get(position);

            String date = AppExtensions.formatFacebookDate(currentItem.getTimeStamp());
            holder.timeStamp.setText(date);

            if (currentItem.getMessage() != null)
                holder.title.setText(currentItem.getMessage());


            if (currentItem.getFullPicture() == null) {
                holder.thumbnail.setVisibility(View.GONE);
            } else {
                holder.thumbnail.setVisibility(View.VISIBLE);
                // Load thumbnail with glide
                Glide.with(context.getApplicationContext())
                        .load(currentItem.getFullPicture())
                        .into(holder.thumbnail);
            }
        }


        /*holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostPhotos.class);
            intent.putExtra(AppConstants.KEY_CURRENT_OBJECT, currentItem);
            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        if (facebookFeedResult == null)
            return 0;
        return facebookFeedResult.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView title, timeStamp;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail_image_card);
            title = itemView.findViewById(R.id.title);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
