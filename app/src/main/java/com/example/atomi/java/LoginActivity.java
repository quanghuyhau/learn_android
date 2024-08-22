//package com.example.atomi.activity;
//
//import static android.content.ContentValues.TAG;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.text.method.PasswordTransformationMethod;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.atomi.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GetTokenResult;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private FirebaseAuth auth;
//
//    private EditText emailInputSi;
//    private EditText passwordInputSi;
//    private TextView buttonSignin;
//    private TextView create_account;
//    private FrameLayout gg;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//
//        auth = FirebaseAuth.getInstance();
//
//        emailInputSi = findViewById(R.id.email_si);
//        passwordInputSi = findViewById(R.id.password_si);
//        buttonSignin = findViewById(R.id.button_signin);
//        create_account = findViewById(R.id.create_account);
//        gg = findViewById(R.id.google);
//
//        passwordInputSi.setTransformationMethod(PasswordTransformationMethod.getInstance());
//
//        buttonSignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickLogin();
//            }
//        });
//
//        gg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,OnBoardingActivity.class));
//            }
//        });
//
//        create_account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//            }
//        });
//    }
//
//    private void clickLogin() {
//        String userEmailSi = emailInputSi.getText().toString().trim();
//        String userPasswordSi = passwordInputSi.getText().toString();
//
//        if (TextUtils.isEmpty(userEmailSi)) {
//            Toast.makeText(this, "Vui lòng nhập Email !", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(userPasswordSi)) {
//            Toast.makeText(this, "Vui lòng nhập Password !", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (userPasswordSi.length() < 6) {
//            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        auth.signInWithEmailAndPassword(userEmailSi, userPasswordSi).addOnCompleteListener(
//                LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = auth.getCurrentUser();
//
//                            if (user != null) {
//                                user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<GetTokenResult> task) {
//                                        if (task.isSuccessful()) {
//                                            String idToken = task.getResult().getToken();
//                                            Log.d(TAG, "ID Token của người dùng là: " + idToken);
//                                        } else {
//                                            Log.e(TAG, "ko lấy được ID Token", task.getException());
//                                        }
//                                    }
//                                });
//
//                                String userEmail = user.getEmail();
//                                String userUid = user.getUid();
//
//                                Log.d(TAG, "ID của người dùng là: " + userUid);
//                                Log.d(TAG, "Email của người dùng là: " + userEmail);
//
//                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                finish();
//                            }
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//}
