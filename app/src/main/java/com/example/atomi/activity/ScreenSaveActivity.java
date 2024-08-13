package com.example.atomi.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.example.atomi.R;

public class ScreenSaveActivity extends AppCompatActivity {

    private TextView buttonLogin;
    private TextView buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);

        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSaveActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "Chuyển sang màn Đăng nhập");
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSaveActivity.this, SignUpActivity.class);
                startActivity(intent);
                Log.d(TAG, "Chuyển sang màn Đăng ký");
            }
        });
    }
}
