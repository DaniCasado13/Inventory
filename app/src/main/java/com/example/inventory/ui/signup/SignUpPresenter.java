package com.example.inventory.ui.signup;

import com.example.inventory.data.model.User;

public class SignUpPresenter implements SignUpContract.SignUpPresenter, SignUpContract.SignUpInteractor {
    private SignUpContract.View view;
    private SignUpInteractor signupinteractor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.signupinteractor = new SignUpInteractor(this);
    }

    @Override
    public void SignUpUser(User user) {
        view.showProgress();
        signupinteractor.SignUpUser(user);

    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    @Override
    public void OnComfirmPasswordEmptyError() {
        view.hideProgress();
        view.SetConfirmPasswordEmptyError();
    }

    @Override
    public void OnPasswordEqualsError() {
        view.hideProgress();
        view.setPassWordEqualsError();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.signupinteractor = null;
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.SetMailEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.SetPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.SetPasswordError();
    }

    @Override
    public void onEmailError() {
        view.hideProgress();
        view.SetMailError();
    }
}
