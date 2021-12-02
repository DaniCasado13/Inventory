package com.example.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.base.OnRepositoryCallback;
import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.LoginRepositoryImpl;
import com.example.inventory.utils.CommonUtils;

public class LoginInteractorImpl implements OnRepositoryCallback {
    private static final long WAIT_TIME = 2000;
    private LoginContract.LoginInteractor loginpresenter;
   // private LoginContract.LoginRepository loginrepository;

    public LoginInteractorImpl(LoginContract.LoginInteractor presenter) {
        this.loginpresenter = presenter;
      // this.loginrepository = LoginRepositoryImpl.newInstance(this);
    }

    public void validateCredentials(User user){

                if (TextUtils.isEmpty(user.getEmail())){
                    loginpresenter.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())){
                    loginpresenter.onPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(user.getPassword())){
                    loginpresenter.onPasswordError();
                    return;
                }
                //loginrepository.login(user);
                LoginRepositoryImpl.newInstance(this).login(user);
            }



    //Respuestas de LoginRepository
    @Override
    public void onSuccess(String message) {
        loginpresenter.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        loginpresenter.onFailure(message);
    }
}
