package com.group4.medgo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.group4.data.db.SQLiteHelper;

import com.group4.medgo.databinding.ActivityMainBinding;
import com.group4.medgo.homepage.HomeFragment;
import com.group4.ui.appointment.AppointmentFragment;
import com.group4.ui.booking.Booking1Activity;
import com.group4.ui.common.DoctorFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //mặc định mở app ở gdien sáng

        // Khởi tạo database nếu chưa có
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        try {
            dbHelper.createDatabaseIfNeeded(); // Copy từ assets nếu cần
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
