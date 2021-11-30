package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;

import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.LoginRepository,SignUpContract.SignUpRepository {
    private static RepositoryStatic instance;
    private LoginContract.OnLoginListener loginlistener;
    private SignUpContract.OnSignUpListener signuplistener;
    private ArrayList<User> users;

    private RepositoryStatic(){

        users=new ArrayList<>();
        initialice();

    }

    private void initialice(){
        users.add(new User("dcasadocarvajal@gmail.com","Dani1234?"));

    }

    private static RepositoryStatic getInstance(LoginContract.OnLoginListener listener){

        if (instance==null)
            instance=new RepositoryStatic();
        instance.loginlistener=listener;
        return instance;
    }
    private static RepositoryStatic getInstance(SignUpContract.OnSignUpListener listener){
        if (instance == null)
            instance = new RepositoryStatic();
        instance.signuplistener = listener;
        return instance;
    }

    @Override
    public void login(User user) {
        for (User item:users){

            if (item.getEmail().equals(user.getEmail())&&item.getPassword().equals(user.getPassword())){
                loginlistener.onSuccess("Usuario Correcto");
                return;

            }

        }

//En caso de que no exista

        loginlistener.onFailure("Error en la autenticación");
    }

    @Override
    public void SignUp(User user) {
        if(users.contains(user))//el usuario existe
        {
            signuplistener.onFailure("El usuario ya existe en el sistema");
            return;
        }
        //en caso contrario lo añadimos
        users.add(user);
        signuplistener.onSuccess("Registro realizado con exito");

    }
}
