package com.group4.medgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.group4.data.db.SQLiteHelper;

import com.group4.medgo.databinding.ActivityMainBinding;
import com.group4.medgo.homepage.ChatbotFragment;
import com.group4.medgo.homepage.HomeFragment;
import com.group4.ui.accountsetting.AccountSettingFragment;
import com.group4.ui.appointment.AppointmentFragment;
import com.group4.ui.booking.Booking1Activity;
import com.group4.ui.common.DoctorFragment;
import com.group4.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // mặc định mở app ở gdien sáng

        // Khởi tạo database nếu chưa có
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        try {
            dbHelper.createDatabaseIfNeeded(); // Copy từ assets nếu cần
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load mặc định HomeFragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
        binding.navbar.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            int itemId = item.getItemId();

            if (itemId == R.id.mnTrangchu) {
                selected = new HomeFragment();
            } else if (itemId == R.id.mnHoso) {
                selected = new ProfileFragment();
            } else if (itemId == R.id.mnLichkham) {
                selected = new AppointmentFragment();
            } else if (itemId == R.id.mnDatlich) {
                //selected = new DoctorFragment(); // hoặc fragment khác
                Intent intent = new Intent(this, Booking1Activity.class);
                startActivity(intent);
                return false; // Không cần load fragment
            } else if (itemId == R.id.mnTaikhoan) {
                selected = new AccountSettingFragment();
            }
            return loadFragment(selected);
        });

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
            View bottomAppBar = findViewById(R.id.bottomAppBar);
            View fabDatlich = findViewById(R.id.fabDatlich);

            if (fragment instanceof ChatbotFragment) {
                // Nếu đang ở ChatbotFragment, ẩn
                if (bottomAppBar != null) bottomAppBar.setVisibility(View.GONE);
                if (fabDatlich != null) fabDatlich.setVisibility(View.GONE);
            } else {
                // Nếu không phải, hiện lại
                if (bottomAppBar != null) bottomAppBar.setVisibility(View.VISIBLE);
                if (fabDatlich != null) fabDatlich.setVisibility(View.VISIBLE);
            }
        });


    }

    private boolean loadFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.container.getId(), fragment)
                .commit();
    return true;

    }


}
