package com.example.atomi.adapter;

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
import com.example.atomi.activity.DetailedActivity;
import com.example.atomi.models.AllProductModel;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    private Context context;

    private List<AllProductModel> allProductModelList;

    public AllProductAdapter(Context context, List<AllProductModel> list) {
        this.context = context;
        this.allProductModelList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(allProductModelList.get(position).getImage()).into(holder.allImg);
        holder.allName.setText(allProductModelList.get(position).getName());
        holder.allPrice.setText(String.valueOf(allProductModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed",allProductModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProductModelList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView allImg;

        TextView allName,allPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            allImg = itemView.findViewById(R.id.all_img);
            allName = itemView.findViewById(R.id.all_product_name);
            allPrice = itemView.findViewById(R.id.all_price);
        }
    }
}
