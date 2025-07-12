package com.group4.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.User;
import com.group4.medgo.MainActivity;
import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityLoginPasswordBinding;
import com.group4.medgo.MainActivity;

import java.io.IOException;

public class LoginPasswordActivity extends AppCompatActivity {

    ActivityLoginPasswordBinding binding;
    SQLiteHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.tvForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(this, ForgetPasswordActivity.class)));

        binding.tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, Register_Activity.class)));

        binding.loginOTP.setOnClickListener(v ->
                startActivity(new Intent(this, LoginOTP_Activity.class)));


        binding.btnLogin.setOnClickListener(view -> {
            String phone = binding.edtPhone.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra tài khoản trong cơ sở dữ liệu
            User user = dbHelper.getUserByPhoneAndPassword(phone, password);

            if (user != null) {
                // Đăng nhập thành công, chuyển sang MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loggedInUser", user); // Truyền user sang MainActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xoá stack đăng nhập
                startActivity(intent);
            } else {
                Toast.makeText(this, "Sai số điện thoại hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
