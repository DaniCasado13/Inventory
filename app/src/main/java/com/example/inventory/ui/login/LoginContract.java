package com.example.inventory.ui.login;

import com.example.inventory.data.model.User;
import com.example.inventory.base.IBasePresenter;
import com.example.inventory.base.IProgressView;
import com.example.inventory.base.OnRepositoryCallback;
public interface LoginContract {

    interface Presenter extends IBasePresenter{
        void validateCredentials(User user);

        void OnDestroy();
    }



    interface LoginRepository{
        void login(User user);

    }
    interface View extends OnRepositoryCallback,IProgressView{
        //Modifica elementos de la vista
        void setUserEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();
        void setUserError();
        void showProgress();
        void hideProgress();
    }
    //Interfaz que tienen que implementar los presentadores que quieran usar la clase LoginInteractor
    interface LoginInteractor extends OnRepositoryCallback{
        void onEmailEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onEmailError();
    }
}
