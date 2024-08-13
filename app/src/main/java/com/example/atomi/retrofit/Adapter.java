package com.example.atomi.retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.atomi.R;
import com.example.atomi.activity.ReadNewsActivity;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position) {
        holder.headlines.setText(modelArrayList.get(position).getTitle());
        holder.mainews.setText(modelArrayList.get(position).getDescription());
        holder.author.setText(modelArrayList.get(position).getAuthor());
        holder.pulishedat.setText(modelArrayList.get(position).getPublishedAt());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, ReadNewsActivity.class);
                    intent.putExtra("URL", modelArrayList.get(adapterPosition).getUrl());
                    context.startActivity(intent);
                }
            }
        });
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView headlines, mainews, author, pulishedat;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            headlines = itemView.findViewById(R.id.mianHealines_id);
            mainews = itemView.findViewById(R.id.newsdescription_id);
            author = itemView.findViewById(R.id.author_id);
            pulishedat = itemView.findViewById(R.id.published_id);
            imageView = itemView.findViewById(R.id.news_image);
        }
    }
}
