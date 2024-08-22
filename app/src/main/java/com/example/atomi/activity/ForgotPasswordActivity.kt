package com.example.atomi.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.atomi.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailInput: EditText
    private lateinit var sendEmailButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        emailInput = findViewById(R.id.email)
        sendEmailButton = findViewById(R.id.change_email)

        sendEmailButton.setOnClickListener {
            sendResetPasswordEmail()
        }
    }

    private fun sendResetPasswordEmail() {
        val email = emailInput.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email khôi phục mật khẩu đã được gửi!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gửi email thất bại: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
