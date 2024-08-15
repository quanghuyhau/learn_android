package com.example.atomi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atomi.R;
import com.example.atomi.models.AllProductModel;
import com.example.atomi.models.NewsProductModel;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView name,description,price;
    TextView rating;
    Button addToCart,buyNow;
    ImageView addItems,remoteItems;

    NewsProductModel newsProductModel = null;
    AllProductModel allProductModel = null;
    private FirebaseFirestore fireStore;

    TextView seeMore;
    boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        fireStore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");
        if (obj instanceof NewsProductModel){
            newsProductModel = (NewsProductModel) obj;
        }

        final Object obj_two = getIntent().getSerializableExtra("detailed");
        if (obj instanceof AllProductModel){
            allProductModel = (AllProductModel) obj_two;
        }

        detailedImg = findViewById(R.id.detailed_img);
        rating = findViewById(R.id.text_detailed_rating);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_desciption);
        price = findViewById(R.id.detailed_price);
        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);
        addItems = findViewById(R.id.add_item);
        remoteItems = findViewById(R.id.remote_item);
        seeMore = findViewById(R.id.detailed_see_more);

        if (newsProductModel != null){
            Glide.with(getApplicationContext()).load(newsProductModel.getImage()).into(detailedImg);
            name.setText(newsProductModel.getName());
            description.setText(newsProductModel.getDescription());
            price.setText(String.valueOf(newsProductModel.getPrice()));
            rating.setText(String.valueOf(newsProductModel.getRating()));
//            rating.setRating(Float.parseFloat(newsProductModel.getRating()));
            description.post(new Runnable() {
                @Override
                public void run() {
                    if (description.getLineCount() > 10) {
                        seeMore.setVisibility(View.VISIBLE);
                        description.setMaxLines(10);
                        description.setEllipsize(TextUtils.TruncateAt.END);
                    }
                }

            });

        }
        if (allProductModel != null){
            Glide.with(getApplicationContext()).load(allProductModel.getImage()).into(detailedImg);
            name.setText(allProductModel.getName());
            description.setText(allProductModel.getDescription());
            price.setText(String.valueOf(allProductModel.getPrice()));
            rating.setText(String.valueOf(allProductModel.getRating()));
//            rating.setRating(Float.parseFloat(newsProductModel.getRating()));
            description.post(new Runnable() {
                @Override
                public void run() {
                    if (description.getLineCount() > 10) {
                        seeMore.setVisibility(View.VISIBLE);
                        description.setMaxLines(10);
                        description.setEllipsize(TextUtils.TruncateAt.END);
                    }
                }

            });

        }


        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpanded) {
                    description.setMaxLines(10);
                    description.setEllipsize(TextUtils.TruncateAt.END);
                    seeMore.setText("Xem thêm...");
                    isExpanded = false;
                } else {
                    description.setMaxLines(Integer.MAX_VALUE);
                    description.setEllipsize(null);
                    seeMore.setText("Thu gọn");
                    isExpanded = true;
                }
            }
        });
    }

    }
