package com.group4.medgo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.group4.data.db.SQLiteHelper;

import com.group4.medgo.databinding.ActivityMainBinding;
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
                Intent intent = new Intent(this, Booking1Activity.class);
                startActivity(intent);
                return false; // Không cần load fragment
            } else if (itemId == R.id.mnTaikhoan) {
                selected = new AccountSettingFragment();
            }
            return loadFragment(selected);
        });
        // ====== NEW: Xử lý FloatingActionButton ======
        binding.fabDatlich.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Booking1Activity.class))
        );
        // =============================================
        // Hiển thị DoctorFragment
//        getSupportFragmentManager().beginTransaction()
//                .replace(android.R.id.content, new DoctorFragment())
//                .commit();
    }

    private boolean loadFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.container.getId(), fragment)
                .commit();
    return true;

    }


}
