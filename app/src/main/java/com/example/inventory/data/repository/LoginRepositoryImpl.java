package com.example.inventory.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.base.Event;
import com.example.inventory.base.OnRepositoryCallback;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.signup.SignUpContract;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.greenrobot.eventbus.EventBus;


//Simulamos que la instancia del repository es unica (Patron singleton)
public class LoginRepositoryImpl implements LoginContract.LoginRepository, SignUpContract.SignUpRepository {
    private static LoginRepositoryImpl instance;
    // private LoginContract.OnLoginListener listener;
    private OnRepositoryCallback listener;

    private static final String TAG = "LoginRepositoryImpl";

    private LoginRepositoryImpl() {
    }

    public static LoginRepositoryImpl newInstance(OnRepositoryCallback loginListener) {
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }
        instance.listener = loginListener;
        return instance;
    }

    @Override
    public void login(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            listener.onSuccess("Usuario correcto");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //listener.onFailure("Error Autenticacion:"+task.getException());

                            Event event = new Event();
                            event.setEventType(Event.onLoginError);
                            event.setMessage("Error de autenticai??n eventbus");
                            EventBus.getDefault().post(event);

                        }
                    }
                });
    }

    @Override
    public void SignUp(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            listener.onSuccess("Usuario Creado correctamente");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            listener.onFailure("Error creaci??n de usuario: " + task.getException());
                        }
                    }
                });

    }
}
