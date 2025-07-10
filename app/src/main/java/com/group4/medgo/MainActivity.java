package com.group4.medgo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.group4.data.db.SQLiteHelper;
import com.group4.medgo.homepage.HomeFragment;
import com.group4.ui.common.DoctorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo database nếu chưa có
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        try {
            dbHelper.createDatabaseIfNeeded(); // Copy từ assets nếu cần
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Hiển thị Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new HomeFragment())
                .commit();
    }
}
