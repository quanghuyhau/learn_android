package com.example.atomi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atomi.R
import com.example.atomi.adapter.MyCartAdapter
import com.example.atomi.retrofit.MyCartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class CartActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartModelList: MutableList<MyCartModel>
    private lateinit var cartAdapter: MyCartAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        toolbar = findViewById(R.id.my_cart_tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.cart_rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartModelList = mutableListOf()
        cartAdapter = MyCartAdapter(this, cartModelList)
        recyclerView.adapter = cartAdapter

        firestore.collection("AddToCart")
            .document(auth.currentUser?.uid ?: "")
            .collection("User")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (doc in task.result?.documents ?: emptyList()) {
                        val myCartModel = doc.toObject(MyCartModel::class.java)
                        myCartModel?.let {
                            cartModelList.add(it)
                            cartAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
    }
}
