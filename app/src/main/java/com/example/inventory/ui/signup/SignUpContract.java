package com.example.inventory.ui.signup;

import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.base.OnRepositoryCallback;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;

public interface SignUpContract {

    interface SignUpRepository {
        void SignUp(User user);


    }

    interface SignUpInteractor extends LoginContract.LoginInteractor {

        void OnComfirmPasswordEmptyError();

        void OnPasswordEqualsError();

    }

    interface SignUpPresenter extends IBasePresenter {
        void SignUpUser(User user);
    }

    interface View extends OnRepositoryCallback,IProgressView {


        void SetPasswordEmptyError();

        void SetConfirmPasswordEmptyError();

        void SetPasswordError();

        void SetMailEmptyError();

        void SetMailError();

        void setPassWordEqualsError();


    }


}
