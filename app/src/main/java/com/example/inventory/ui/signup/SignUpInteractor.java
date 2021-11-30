package com.example.inventory.ui.signup;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.inventory.data.model.User;

import com.example.inventory.utils.CommonUtils;

public class SignUpInteractor implements SignUpContract.OnSignUpListener {
    private SignUpContract.SignUpRepository signuprepository;
    private SignUpContract.SignUpInteractor signupinteractor;


    public SignUpInteractor(SignUpContract.SignUpInteractor interactor) {
        this.signupinteractor = interactor;
        this.signuprepository = SignUpRepository.newInstance(this);

    }

    //reglas de negocio
    public void SignUpUser(User user) {
        if (TextUtils.isEmpty(user.getEmail())) {
            signupinteractor.OnMailEmptyError();
            return;
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            signupinteractor.OnPasswordEmptyError();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            signupinteractor.OnMailError();
            return;
        }
        if (!CommonUtils.isPasswordValid(user.getPassword())) {
            signupinteractor.OnPasswordError();
            return;
        }
        signuprepository.SignUp(user);
    }

    @Override
    public void onSuccess(String message) {
        signupinteractor.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        signupinteractor.onFailure(message);
    }
}
