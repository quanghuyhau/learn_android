package com.example.atomi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atomi.R
import com.example.atomi.adapter.AddressAdapter
import com.example.atomi.models.AddressModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class AddressActivity : AppCompatActivity(), AddressAdapter.SelectedAddress {

    private lateinit var addAddress: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var paymentBtn: Button
    private lateinit var addressModelList: MutableList<AddressModel>
    private lateinit var addressAdapter: AddressAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var mAddress: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        addAddress = findViewById(R.id.add_address_btn)
        paymentBtn = findViewById(R.id.payment_btn)
        recyclerView = findViewById(R.id.address_recycler)

        addressModelList = mutableListOf()
        addressAdapter = AddressAdapter(applicationContext, addressModelList, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = addressAdapter

        firestore.collection("CurrentUser").document(auth.currentUser?.uid ?: "")
            .collection("Address").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.documents?.forEach { doc ->
                        val addressModel = doc.toObject(AddressModel::class.java)
                        addressModel?.let {
                            addressModelList.add(it)
                            addressAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

        paymentBtn.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }
        addAddress.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }
    }

    override fun setAddress(address: String) {
        mAddress = address
    }
}
