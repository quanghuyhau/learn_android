package com.example.atomi.activity;


public class SignUpPresenter {
    private SignUpInterface signUpInterface;

    public SignUpPresenter(SignUpInterface signUpInterface) {
        this.signUpInterface = signUpInterface;
    }

    public void SignUp(UserSignUp userSignUp){
        if (userSignUp.isValidEmailSu() && userSignUp.isValidPasswordSu() && userSignUp.isPasswordConfirmed()) {
            signUpInterface.signUpSuccess();

        } else {
           signUpInterface.signUpError();
        }
    }
}
