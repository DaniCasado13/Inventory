package com.example.inventory.ui.signup;

import com.example.inventory.data.model.User;

public interface SignUpContract {

    interface SignUpRepository {
        void SignUp(User user);


    }

    interface OnSignUpListener {
        void onSuccess(String message);

        void onFailure(String message);
    }

    interface SignUpInteractor extends OnSignUpListener {

        void OnMailError();

        void OnMailEmptyError();

        void OnPasswordError();

        void OnPasswordEmptyError();

        void OnComfirmPasswordEmptyError();

        void OnPasswordEqualsError();

    }

    interface SignUpPresenter {
        void SignUpUser(User user);
    }

    interface View extends OnSignUpListener {
        void SetUserEmptyError();

        void SetPasswordEmptyError();

        void SetConfirmPasswordEmptyError();

        void SetPasswordError();

        void SetMailEmptyError();

        void SetMailError();

        void setPassWordEqualsError();

        void ShowProgressBar();

        void HideProgressBar();
    }


}
