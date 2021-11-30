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
        view.ShowProgressBar();
        signupinteractor.SignUpUser(user);

    }

    @Override
    public void onSuccess(String message) {
        view.HideProgressBar();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.HideProgressBar();
        view.onFailure(message);
    }

    @Override
    public void OnMailError() {
        view.HideProgressBar();
        view.SetMailError();
    }

    @Override
    public void OnMailEmptyError() {
        view.HideProgressBar();
        view.SetMailEmptyError();
    }

    @Override
    public void OnPasswordError() {
        view.HideProgressBar();
        view.SetPasswordError();
    }

    @Override
    public void OnPasswordEmptyError() {
        view.HideProgressBar();
        view.SetPasswordEmptyError();
    }

    @Override
    public void OnComfirmPasswordEmptyError() {
        view.HideProgressBar();
        view.SetConfirmPasswordEmptyError();
    }

    @Override
    public void OnPasswordEqualsError() {
        view.HideProgressBar();
        view.setPassWordEqualsError();
    }
}
