package com.example.inventory.ui.signup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivitySignUpBinding;
import com.example.inventory.ui.login.LoginActivity;
import com.example.inventory.utils.CommonUtils;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {
    private static final long WAIT_TIME = 2000;
    private ActivitySignUpBinding binding;
    private SignUpContract.SignUpPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SignUpPresenter(this);

        binding.btnEnviarSignUp.setOnClickListener(v -> presenter.SignUpUser(new User(binding.tilEmail.getEditText().getText().toString(), binding.tilPassword.getEditText().getText().toString())));
        binding.tilEmail.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilEmail.getEditText()));
        binding.tilPassword.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilPassword.getEditText()));
        binding.tilConfirmPassword.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilConfirmPassword.getEditText()));
        binding.tilUser.getEditText().addTextChangedListener(new SignUpTextWatcher(binding.tilUser.getEditText()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
            presenter.onDestroy();
    }

    @Override
    public void onSuccess(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, WAIT_TIME);
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void SetPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
    }

    @Override
    public void SetConfirmPasswordEmptyError() {
        binding.tilConfirmPassword.setError(getString(R.string.passwordEmptyError));
    }

    @Override
    public void SetPasswordError() {
        binding.tilPassword.setError(getString(R.string.passwordError));
    }

    @Override
    public void SetMailEmptyError() {
        binding.tilEmail.setError(getString(R.string.emailEmptyError));
    }

    @Override
    public void SetMailError() {
        binding.tilEmail.setError(getString(R.string.emailError));
    }

    @Override
    public void setPassWordEqualsError() {
        binding.tilPassword.setError(getString(R.string.notEqualsPasswordError));
        binding.tilConfirmPassword.setError(getString(R.string.notEqualsPasswordError));
    }



    @Override
    public void showProgress() {
        binding.pbSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbSignUp.setVisibility(View.INVISIBLE);
    }

    class SignUpTextWatcher implements TextWatcher {
        private final View view;

        public SignUpTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.tiledtEmail:
                    if (TextUtils.isEmpty(s.toString())) {
                        binding.tilEmail.setError(getString(R.string.emailEmptyError));
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                        binding.tilEmail.setError(getString(R.string.emailError));
                    } else {
                        binding.tilEmail.setError(null);
                    }
                    break;
                case R.id.tiedUserName:
                    if (TextUtils.isEmpty(s.toString())) {
                        binding.tilUser.setError(getString(R.string.nameEmptyError));
                    } else {
                        binding.tilUser.setError(null);
                    }
                    break;
                case R.id.tiledtPassword:
                    if (TextUtils.isEmpty(s.toString())) {
                        binding.tilPassword.setError(getString(R.string.passwordEmptyError));
                    } else if (!CommonUtils.isPasswordValid(s.toString())) {
                        binding.tilPassword.setError(getString(R.string.passwordError));
                    } else  {
                        binding.tilPassword.setError(null);
                    }
                    break;
                case R.id.tiedConfirmPassword:
                    if (TextUtils.isEmpty(s.toString())) {
                        binding.tilConfirmPassword.setError(getString(R.string.passwordEmptyError));
                    } else {
                        binding.tilConfirmPassword.setError(null);
                    }
                    break;

            }
        }

    }
}