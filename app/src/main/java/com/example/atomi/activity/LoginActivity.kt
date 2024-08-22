package com.example.atomi.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.atomi.R
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailInputSi: EditText
    private lateinit var passwordInputSi: EditText
    private lateinit var buttonSignin: TextView
    private lateinit var createAccount: TextView
    private lateinit var forgotPassword: TextView
    private lateinit var gg: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        emailInputSi = findViewById(R.id.email_si)
        passwordInputSi = findViewById(R.id.password_si)
        buttonSignin = findViewById(R.id.button_signin)
        createAccount = findViewById(R.id.create_account)
        forgotPassword = findViewById(R.id.forgot_password)
        gg = findViewById(R.id.google)

        passwordInputSi.transformationMethod = PasswordTransformationMethod.getInstance()

        buttonSignin.setOnClickListener { clickLogin() }

        gg.setOnClickListener {
            startActivity(Intent(this@LoginActivity, OnBoardingActivity::class.java))
        }

        createAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }

        forgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }

    private fun clickLogin() {
        val userEmailSi = emailInputSi.text.toString().trim()
        val userPasswordSi = passwordInputSi.text.toString()

        if (TextUtils.isEmpty(userEmailSi)) {
            Toast.makeText(this, "Vui lòng nhập Email!", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(userPasswordSi)) {
            Toast.makeText(this, "Vui lòng nhập Password!", Toast.LENGTH_SHORT).show()
            return
        }
        if (userPasswordSi.length < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(userEmailSi, userPasswordSi)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser

                    user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                        if (tokenTask.isSuccessful) {
                            val idToken = tokenTask.result?.token
                            Log.d(TAG, "ID Token của người dùng là: $idToken")
                        } else {
                            Log.e(TAG, "ko lấy được ID Token", tokenTask.exception)
                        }
                    }

                    user?.let {
                        val userEmail = it.email
                        val userUid = it.uid

                        Log.d(TAG, "ID của người dùng là: $userUid")
                        Log.d(TAG, "Email của người dùng là: $userEmail")

                        Toast.makeText(this@LoginActivity, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Đăng nhập thất bại: ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
