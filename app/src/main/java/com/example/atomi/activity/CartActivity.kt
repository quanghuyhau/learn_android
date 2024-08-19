package com.example.atomi.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atomi.R
import com.example.atomi.adapter.MyCartAdapter
import com.example.atomi.models.MyCartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
class CartActivity : AppCompatActivity() {

    private var overAllTotalAmount: Int = 0
    private lateinit var overAllAmount: TextView
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

        overAllAmount = findViewById(R.id.all_price)
        recyclerView = findViewById(R.id.cart_rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cartModelList = mutableListOf()
        cartAdapter = MyCartAdapter(this, cartModelList)
        recyclerView.adapter = cartAdapter

        firestore.collection("AddToCart").document(auth.currentUser?.uid ?: "")
            .collection("User").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    overAllTotalAmount = 0
                    cartModelList.clear()
                    task.result?.documents?.forEach { doc ->
                        val myCartModel = doc.toObject(MyCartModel::class.java)
                        myCartModel?.let {
                            cartModelList.add(it)
                            overAllTotalAmount += it.totalPrice ?: 0
                        }
                    }
                    cartAdapter.notifyDataSetChanged()
                    overAllAmount.text = "Giá sản phẩm : $overAllTotalAmount đ"
                }
            }
    }

//    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context, intent: Intent) {
//            val totalBill = intent.getIntExtra("totalAmount", 0)
//            overAllAmount.text = "Tổng đơn hàng : $totalBill đ"
//        }
//    }
}
