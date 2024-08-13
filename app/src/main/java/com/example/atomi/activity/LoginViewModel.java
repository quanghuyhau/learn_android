//package com.example.atomi;
//
//import androidx.databinding.BaseObservable;
//import androidx.databinding.Bindable;
//import androidx.databinding.ObservableField;
//
//public class LoginViewModel extends BaseObservable {
//
//    private String email;
//    private String password;
//
//    public ObservableField<String> messageLogin = new ObservableField<>();
//
//
//    @Bindable
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//        notifyPropertyChanged(BR.email);
//    }
//
//    @Bindable
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//        notifyPropertyChanged(BR.password);
//    }
//
//    public void onClickLogin(){
//        User user = new User(getEmail(), getPassword());
//
//        if (user.isValidEmail() && user.isValidPassword()){
//            messageLogin.set("Đăng nhập thành công");
//        } else {
//            messageLogin.set("Email hoặc password không chính xác");
//
//        }
//    }
//}
