package com.example.atomi.activity;

import android.text.TextUtils;
import android.util.Patterns;

public class UserSignUp {
    private String emailSu;
    private String passwordSu;

    private String confirmPassword;

    public String getEmailSu() {
        return emailSu;
    }

    public void setEmailSu(String emailSu) {
        this.emailSu = emailSu;
    }

    public String getPasswordSu() {
        return passwordSu;
    }

    public void setPasswordSu(String passwordSu) {
        this.passwordSu = passwordSu;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserSignUp(String emailSu, String passwordSu, String confirmPassword) {
        this.emailSu = emailSu;
        this.passwordSu = passwordSu;
        this.confirmPassword = confirmPassword;
    }

    public boolean isValidEmailSu(){
        return  !TextUtils.isEmpty(emailSu) && Patterns.EMAIL_ADDRESS.matcher(emailSu).matches();
    }

    public boolean isValidPasswordSu(){
        return !TextUtils.isEmpty(passwordSu) && passwordSu.length() >= 6;
    }

    public boolean isPasswordConfirmed() {
        return passwordSu.equals(confirmPassword);
    }
}