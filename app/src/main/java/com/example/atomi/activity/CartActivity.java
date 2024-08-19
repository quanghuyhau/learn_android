//package com.example.atomi.activity;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.atomi.R;
//import com.example.atomi.adapter.MyCartAdapter;
//import com.example.atomi.retrofit.MyCartModel;
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
//    Toolbar toolbar;
//    RecyclerView recyclerView;
//    List<MyCartModel> cartModelList;
//    MyCartAdapter cartAdapter;
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
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        recyclerView = findViewById(R.id.cart_rec);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        cartModelList = new ArrayList<>();
//        cartAdapter = new MyCartAdapter(this, cartModelList);
//        recyclerView.setAdapter(cartAdapter);
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
//
//
//    }
//}