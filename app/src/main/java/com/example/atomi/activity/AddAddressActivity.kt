package com.example.atomi.activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.atomi.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddAddressActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var city: EditText
//    private lateinit var postalCode: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var toolbar: Toolbar
    private lateinit var addAddressBtn: Button

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

//       toolbar.setNavigationIcon(R.drawable.ic_back)
//        toolbar.navigationIcon?.setTint(resources.getColor(R.color.white))
//        toolbar.setNavigationOnClickListener {
//            finish()
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address_activity)

        toolbar = findViewById(R.id.add_address_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        name = findViewById(R.id.ad_name)
        address = findViewById(R.id.ad_address)
        city = findViewById(R.id.ad_city)
//        postalCode = findViewById(R.id.ad_code)
        phoneNumber = findViewById(R.id.ad_phone)
        addAddressBtn = findViewById(R.id.ad_add_address)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        addAddressBtn.setOnClickListener {
            val userName = name.text.toString()
            val userAddress = address.text.toString()
            val userCity = city.text.toString()
            val userNumber = phoneNumber.text.toString()

            var finalAddress = ""

            if (userName.isNotEmpty()) {
                finalAddress += "$userName, "
            }

            if (userCity.isNotEmpty()) {
                finalAddress += "$userCity, "
            }
            if (userAddress.isNotEmpty()) {
                finalAddress += "$userAddress, "
            }

            if (userNumber.isNotEmpty()) {
                finalAddress += userNumber
            }

            if (finalAddress.endsWith(", ")) {
                finalAddress = finalAddress.substring(0, finalAddress.length - 2)
            }

            if (userName.isNotEmpty() && userCity.isNotEmpty() && userAddress.isNotEmpty() && userNumber.isNotEmpty()) {
                val map = hashMapOf<String, String>()
                map["userAddress"] = finalAddress

                firestore.collection("CurrentUser").document(auth.currentUser?.uid ?: "")
                    .collection("Address").add(map)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Thêm địa chỉ thành công", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@AddAddressActivity, AddressActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
