//package com.example.atomi.activity;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.atomi.R;
//import com.example.atomi.adapter.MyCartAdapter;
//import com.example.atomi.models.MyCartModel;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartActivity extends AppCompatActivity {
//
//    int overAllTotalAmount;
//
//    TextView overAllAmount;
//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    List<MyCartModel> cartModelList;
//    MyCartAdapter cartAdapter;
//    Button addAddressBtn;
//    private FirebaseAuth auth;
//    private FirebaseFirestore firestore;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);
//
//        auth = FirebaseAuth.getInstance();
//        firestore = FirebaseFirestore.getInstance();
//
//        toolbar = findViewById(R.id.my_cart_tool_bar);
//        addAddressBtn = findViewById(R.id.add_to_cart);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));
//
//        overAllAmount = findViewById(R.id.all_price);
//        recyclerView = findViewById(R.id.cart_rec);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        cartModelList = new ArrayList<>();
//        cartAdapter = new MyCartAdapter(this, cartModelList);
//        recyclerView.setAdapter(cartAdapter);
//
//
//        addAddressBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CartActivity.this, AddressActivity.class));
//            }
//        });
//
//
//        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).
//                    collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
//                        MyCartModel myCartModel = doc.toObject(MyCartModel.class);
//                        cartModelList.add(myCartModel);
//                        cartAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//        });
//    }
//    public BroadcastReceiver mMessageReceiver= new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            int totalBill = intent.getIntExtra("totalAmount",0);
//            overAllAmount.setText("Giá sản phẩm : " + totalBill + " đ");
//        }
//    };
//}