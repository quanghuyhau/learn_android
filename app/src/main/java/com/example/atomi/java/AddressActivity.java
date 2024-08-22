//package com.example.atomi.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.Toolbar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.atomi.R;
//import com.example.atomi.adapter.AddressAdapter;
//import com.example.atomi.models.AddressModel;
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
//public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {
//
//    Button addAddress;
//    RecyclerView recyclerView;
//    Toolbar toolbar;
//    private List<AddressModel> addressModelList;
//    private AddressAdapter addressAdapter;
//    FirebaseAuth auth;
//    FirebaseFirestore firestore;
//    Button addAddressBtn,paymentBtn;
//    String mAddress = "";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_address);
//
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//
//
//        firestore = FirebaseFirestore.getInstance();
//        auth= FirebaseAuth.getInstance();
//        addAddress = findViewById(R.id.add_address_btn);
//        addressModelList = new ArrayList<>();
//        paymentBtn = findViewById(R.id.payment_btn);
//        recyclerView = findViewById(R.id.address_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        addressAdapter = new AddressAdapter(getApplicationContext(),addressModelList,this);
//        recyclerView.setAdapter(addressAdapter);
//
//        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
//                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        if (task.isSuccessful()){
//                            for(DocumentSnapshot doc : task.getResult().getDocuments()){
//                                AddressModel addressModel = doc.toObject(AddressModel.class);
//                                addressModelList.add(addressModel);
//                                addressAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//                    }
//                });
//
//
////        toolbar = findViewById(R.id.address_toolbar);
////        getSupportActionBar(toolbar);
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        paymentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddressActivity.this,PaymentActivity.class));
//            }
//        });
//        addAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
//            }
//        });
//    }
//
//    @Override
//    public void setAddress(String address) {
//        mAddress =address;
//    }
//}