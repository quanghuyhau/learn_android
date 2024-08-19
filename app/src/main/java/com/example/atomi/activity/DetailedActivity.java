package com.example.atomi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.atomi.R;
import com.example.atomi.models.AllProductModel;
import com.example.atomi.models.NewsProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class  DetailedActivity extends AppCompatActivity {


    ImageView detailedImg;
    TextView name, description, price,quantity;
    TextView rating;
    Button addToCart, buyNow;
    ImageView addItems, remoteItems;


    int totalQuantity = 1;
    int totalPrice = 0;

    NewsProductModel newsProductModel = null;
    AllProductModel allProductModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore fireStore;

    TextView seeMore;
    boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");
        if (obj instanceof NewsProductModel) {
            newsProductModel = (NewsProductModel) obj;
        }

        final Object obj_two = getIntent().getSerializableExtra("detailed");
        if (obj instanceof AllProductModel) {
            allProductModel = (AllProductModel) obj_two;
        }

        detailedImg = findViewById(R.id.detailed_img);
        quantity = findViewById(R.id.quantity);
        rating = findViewById(R.id.text_detailed_rating);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_desciption);
        price = findViewById(R.id.detailed_price);
        addToCart = findViewById(R.id.add_to_cart);
//        buyNow = findViewById(R.id.buy_now);
        addItems = findViewById(R.id.add_item);
        remoteItems = findViewById(R.id.remote_item);
        seeMore = findViewById(R.id.detailed_see_more);

        if (newsProductModel != null) {
            Glide.with(getApplicationContext()).load(newsProductModel.getImage()).into(detailedImg);
            name.setText(newsProductModel.getName());
            description.setText(newsProductModel.getDescription());
            price.setText(String.valueOf(newsProductModel.getPrice()));
            rating.setText(String.valueOf(newsProductModel.getRating()));


            String priceString = newsProductModel.getPrice().replace(".", "");
            try {
                int price = Integer.parseInt(priceString);
                totalPrice = price * totalQuantity;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


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
        if (allProductModel != null) {
            Glide.with(getApplicationContext()).load(allProductModel.getImage()).into(detailedImg);
            name.setText(allProductModel.getName());
            description.setText(allProductModel.getDescription());
            price.setText(String.valueOf(allProductModel.getPrice()));
            rating.setText(String.valueOf(allProductModel.getRating()));

            String priceString = allProductModel.getPrice().replace(".", "");
            try {
                int price = Integer.parseInt(priceString);
                totalPrice = price * totalQuantity;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

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


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });

        remoteItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remoteItems();
            }
        });
    }


    private void addToCart() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("productImage", newsProductModel != null ? newsProductModel.getImage() : allProductModel.getImage()); // Lưu URL hình ảnh
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        fireStore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailedActivity.this, "Thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DetailedActivity.this, "Lỗi khi thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private  void addItems(){
        if (totalQuantity < 100 ){
            totalQuantity++;
            quantity.setText(String.valueOf(totalQuantity));

            if (newsProductModel != null){
                String priceString = newsProductModel.getPrice().replace(".", "");
                try {
                    int price = Integer.parseInt(priceString);
                    totalPrice = price * totalQuantity;
                } catch (NumberFormatException e) {
            }
        }
            if (allProductModel != null){
                String priceString = allProductModel.getPrice().replace(".", "");
                try {
                    int price = Integer.parseInt(priceString);
                    totalPrice = price * totalQuantity;
                } catch (NumberFormatException e){

                }
            }
            Toast.makeText(DetailedActivity.this, "Đã thêm 1 sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

    private  void remoteItems(){
        if(totalQuantity > 1){
            totalQuantity--;
            quantity.setText(String.valueOf(totalQuantity));

            Toast.makeText(DetailedActivity.this, "Đã xóa 1 sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }

}
