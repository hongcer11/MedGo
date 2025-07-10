package com.group4.ui.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityLoginOtpBinding;

public class LoginOTP_Activity extends AppCompatActivity {

    ActivityLoginOtpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();

    }

    private void addEvents() {
        binding.loginPass.setOnClickListener(v -> {
            startActivity(new Intent(LoginOTP_Activity.this, LoginPasswordActivity.class));
        });
    }
}