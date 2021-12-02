package com.example.inventory.data.repository;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;
import com.example.inventory.base.OnRepositoryCallback;
import java.util.ArrayList;

public class RepositoryStatic implements LoginContract.LoginRepository,SignUpContract.SignUpRepository {
    private static RepositoryStatic instance;
   // private LoginContract.OnLoginListener loginlistener;
   // private SignUpContract.OnSignUpListener signuplistener;
    private static OnRepositoryCallback callback;
    private ArrayList<User> users;

    private RepositoryStatic(){

        users=new ArrayList<>();
        initialice();

    }

    private void initialice(){
        users.add(new User("dcasadocarvajal@gmail.com","Dani1234?"));

    }

    private static RepositoryStatic getInstance(OnRepositoryCallback listener){

        if (instance==null)
            instance=new RepositoryStatic();
       callback=listener;
        return instance;
    }

    @Override
    public void login(User user) {
        for (User item:users){

            if (item.getEmail().equals(user.getEmail())&&item.getPassword().equals(user.getPassword())){
                callback.onSuccess("Usuario Correcto");
                return;

            }

        }

//En caso de que no exista

        callback.onFailure("Error en la autenticación");
    }

    @Override
    public void SignUp(User user) {
        if(users.contains(user))//el usuario existe
        {
            callback.onFailure("El usuario ya existe en el sistema");
            return;
        }
        //en caso contrario lo añadimos
        users.add(user);
        callback.onSuccess("Registro realizado con exito");

    }
}
