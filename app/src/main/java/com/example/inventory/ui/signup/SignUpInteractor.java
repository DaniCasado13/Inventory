package com.example.inventory.ui.signup;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.inventory.base.OnRepositoryCallback;
import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.LoginRepositoryImpl;
import com.example.inventory.utils.CommonUtils;

public class SignUpInteractor implements OnRepositoryCallback {
    private SignUpContract.SignUpRepository signuprepository;
    private SignUpContract.SignUpInteractor signupinteractor;


    public SignUpInteractor(SignUpContract.SignUpInteractor interactor) {
        this.signupinteractor = interactor;
        this.signuprepository = LoginRepositoryImpl.newInstance(this);

    }

    //reglas de negocio
    public void SignUpUser(User user) {
        if (TextUtils.isEmpty(user.getEmail())) {
            signupinteractor.onEmailEmptyError();
            return;
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            signupinteractor.onPasswordEmptyError();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            signupinteractor.onEmailError();
            return;
        }
        if (!CommonUtils.isPasswordValid(user.getPassword())) {
            signupinteractor.onPasswordError();
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
