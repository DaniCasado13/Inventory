package com.example.inventory.ui.signup;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.inventory.base.OnRepositoryCallback;
import com.example.inventory.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//dcasadocarvajal@gmail.com Dani1234?
public class SignUpRepository implements SignUpContract.SignUpRepository {
    private static SignUpRepository instance;
    private static OnRepositoryCallback signuplistener;
    private static final String TAG = SignUpRepository.class.getName();

    private SignUpRepository() {
    }

    public static SignUpRepository newInstance(OnRepositoryCallback listener) {
        if (instance == null) {
            instance = new SignUpRepository();
        }
        instance.signuplistener = listener;
        return instance;
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
                            signuplistener.onSuccess("Usuario registrado correctamente");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            signuplistener.onFailure("Error en el registro: " + task.getException());
                        }
                    }
                });
    }
}
