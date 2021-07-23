package com.example.grato_gv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;

public class LoginActivity extends AppCompatActivity implements GratoViewModel.LoginFailListener {

    EditText editUsername, editPassword;
    Button btnLogin;
    GratoViewModel mGratoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truong_login);

        addControls();
        addEvents();


    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        if (!validateUsername() || !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = editUsername.getText().toString();
        final String userEnteredPassword = editPassword.getText().toString();

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.setLoginFailListener(this);

        mGratoViewModel.getLoginResponse().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse == null) {
                    Toast.makeText(LoginActivity.this, "Account not exist! Login fail!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // 1. Login and save session
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    SessionManagement sessionManagement = SessionManagement.getInstance(LoginActivity.this);
                    sessionManagement.saveSession(loginResponse);

                    // 2. Move to Subject Activity
//                    Toast.makeText(LoginActivity.this, loginResponse.getToken() + loginResponse.getUser().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,TruongListSubjectActivity.class);
                    startActivity(intent);
                }
            }
        });

        mGratoViewModel.login(userEnteredUsername, userEnteredPassword);
    }

    private boolean validatePassword() {
        String val = editPassword.getText().toString();

        if (val.isEmpty()) {
            editPassword.setError("Password cannot be empty.");
            return false;
        } else {
            editPassword.setError(null);
//            editPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = editUsername.getText().toString();

        if (val.isEmpty()) {
            editUsername.setError("Username cannot be empty.");
            return false;
        }  else {
            editUsername.setError(null);
//            editUsername.seterrr(false);
            return true;
        }
    }

    private void addControls() {
        editUsername = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(this, "Net work error!", Toast.LENGTH_SHORT).show();
    }
}