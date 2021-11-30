package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



//Simulamos que la instancia del repository es unica (Patron singleton)
public class RepositoryImpl implements LoginContract.LoginRepository,SignUpContract.SignUpRepository{
    private static RepositoryImpl instance;
    private LoginContract.OnLoginListener listener;
    private SignUpContract.OnSignUpListener signuplistener;
    private static final String TAG= "RepositoryImpl";

    private RepositoryImpl() {
    }

    public static RepositoryImpl newInstance(LoginContract.OnLoginListener loginListener){
        if (instance == null){
            instance = new RepositoryImpl();
        }
        instance.listener = loginListener;
        return instance;
    }
    public static RepositoryImpl newInstance(SignUpContract.OnSignUpListener signuplistener){
        if (instance == null){
            instance = new RepositoryImpl();
        }
        instance.signuplistener = signuplistener;
        return instance;
    }
    @Override
    public void login(User user) {
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            listener.onSuccess("Usuario correcto");

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            listener.onFailure("Error Autenticacion:"+task.getException());


                        }
                    }
                });
    }

    @Override
    public void SignUp(User user) {
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signUpWithEmail:success");
                            signuplistener.onSuccess("Registro correcto");

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signUpWithEmail:failure", task.getException());
                            signuplistener.onFailure("Error en el Registro:"+task.getException());


                        }
                    }
                });
    }
}
