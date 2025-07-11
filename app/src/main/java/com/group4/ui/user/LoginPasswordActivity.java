package com.group4.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityLoginPasswordBinding;

public class LoginPasswordActivity extends AppCompatActivity {

    ActivityLoginPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEvents();
    }

    private void addEvents() {
        binding.tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginPasswordActivity.this, ForgetPasswordActivity.class));
        });

        binding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginPasswordActivity.this, Register_Activity.class));
        });

        binding.loginOTP.setOnClickListener(v -> {
            startActivity(new Intent(LoginPasswordActivity.this, LoginOTP_Activity.class));
        });
    }
}