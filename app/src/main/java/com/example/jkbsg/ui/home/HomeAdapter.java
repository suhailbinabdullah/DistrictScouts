package com.example.jkbsg.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jkbsg.R;
import com.example.jkbsg.pojos.Model;
import com.example.jkbsg.pojos.ModelResult;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private ModelResult modelResult;

    public HomeAdapter(Context context, ModelResult modelResult) {
        this.context = context;
        this.modelResult = modelResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model currentItem = modelResult.getResults().get(position);
        holder.title.setText(currentItem.getTitle());
        holder.body.setText(currentItem.getBody());
        holder.imageView.setImageResource(R.drawable.suhail);
        holder.author.setText(currentItem.getAuthor());
        holder.timeStamp.setText(currentItem.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return modelResult.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, body, author, timeStamp;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            author = itemView.findViewById(R.id.author);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            imageView = itemView.findViewById(R.id.thumbnail_image_card);
        }
    }
}
