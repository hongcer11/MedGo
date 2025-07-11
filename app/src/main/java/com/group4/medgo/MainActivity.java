package com.group4.medgo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.group4.data.db.SQLiteHelper;
import com.group4.medgo.databinding.ActivityMainBinding;
import com.group4.medgo.homepage.HomeFragment;
import com.group4.ui.accountsetting.AccountSettingFragment;
import com.group4.ui.appointment.AppointmentFragment;
import com.group4.ui.booking.Booking1Activity;
import com.group4.ui.common.DoctorFragment;
import com.group4.ui.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo binding và gán layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                Intent intent = new Intent(this, Booking1Activity.class); // thay BookingActivity bằng activity bạn muốn mở
                startActivity(intent);
                return false; // Không cần load fragment
            } else if (itemId == R.id.mnTaikhoan) {
                selected = new AccountSettingFragment();
            }
            return loadFragment(selected);
        });


        //Hiển thị homeFragment
//        getSupportFragmentManager().beginTransaction()
//                .replace(android.R.id.content, new HomeFragment())
//                .commit();
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
