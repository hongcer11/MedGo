package com.group4.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityRegisterBinding;

public class Register_Activity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.tvAlreadyHaveAccount.setOnClickListener(v -> {
            startActivity(new Intent(Register_Activity.this, LoginPasswordActivity.class));
        });
    }
}