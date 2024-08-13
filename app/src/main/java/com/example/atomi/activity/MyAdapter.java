package com.example.atomi.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atomi.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataHome> dataHomeList;

    public MyAdapter(List<DataHome> dataHomeList) {
        this.dataHomeList = dataHomeList;
    }

    @Override
    public int getItemViewType(int position) {
        return dataHomeList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataHome.TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return dataHomeList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataHome dataHome = dataHomeList.get(position);
        if (holder.getItemViewType() == DataHome.TYPE_HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.headerTitle.setText(dataHome.getDataTitle());
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.imageView.setImageResource(dataHome.getDataImage());
            itemViewHolder.textView.setText(dataHome.getDataTitle());
            itemViewHolder.subTextView.setText(dataHome.getDataSubTitle());
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTitle = itemView.findViewById(R.id.headerTitle);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;
        private TextView subTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.itemTitle);
            subTextView = itemView.findViewById(R.id.itemSubtitle);
        }
    }
}
