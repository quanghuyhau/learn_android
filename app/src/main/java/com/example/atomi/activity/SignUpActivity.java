package com.example.atomi.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atomi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//public class SignUpActivity extends AppCompatActivity implements SignUpInterface {
public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailInputSu;
    private EditText passwordInputSu;
    private EditText confirmPasswordInputSu;
    private EditText nameInputSu;
    private TextView buttonSignupSu;

    private TextView alreadyHaveAnAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        auth = FirebaseAuth.getInstance();
        emailInputSu = findViewById(R.id.email_su);
        passwordInputSu = findViewById(R.id.password_su);
        confirmPasswordInputSu = findViewById(R.id.confirm_password_su);
        nameInputSu = findViewById(R.id.name_su);
        buttonSignupSu = findViewById(R.id.button_signup);
        alreadyHaveAnAccount = findViewById(R.id.have_account);
        passwordInputSu.setTransformationMethod(new PasswordTransformationMethod());
        confirmPasswordInputSu.setTransformationMethod(new PasswordTransformationMethod());


//        signUpPresenter = new SignUpPresenter(this);

        buttonSignupSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSignUp();
            }
        });
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "Chuyển sang màn login");
            }
        });


    }

    public void togglePasswordVisibilitya(View view) {
        int inputType = passwordInputSu.getInputType();
        if (inputType ==(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        } else {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        }
        passwordInputSu.setInputType(inputType);
        passwordInputSu.setSelection(passwordInputSu.getText().length());

        int iconResId;
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
            iconResId = R.drawable.pass2;
        } else {
            iconResId = R.drawable.pass;
        }
        ImageView imageView = findViewById(R.id.show_su);
        imageView.setImageResource(iconResId);
    }
    public void togglePasswordVisibilitys(View view) {
        int inputType = passwordInputSu.getInputType();
        if (inputType ==(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        } else {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        }
        confirmPasswordInputSu.setInputType(inputType);
        confirmPasswordInputSu.setSelection(confirmPasswordInputSu.getText().length());

        int iconResId;
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
            iconResId = R.drawable.pass2;
        } else {
            iconResId = R.drawable.pass;
        }
        ImageView imageView = findViewById(R.id.show_pass_cf_su);
        imageView.setImageResource(iconResId);
    }
    private void clickSignUp() {
        String userName = nameInputSu.getText().toString();
        String userEmail = emailInputSu.getText().toString().trim();
        String userPassword = passwordInputSu.getText().toString();
        String userConfirmPassword = confirmPasswordInputSu.getText().toString();



        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Vui lòng nhập Tên !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Vui lòng nhập Email !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Vui lòng nhập Password !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userConfirmPassword)) {
            Toast.makeText(this, "Vui lòng nhập lại Password !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!userPassword.equals(userConfirmPassword)) {
            Toast.makeText(this, "Mật khẩu không trùng nhau !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



















