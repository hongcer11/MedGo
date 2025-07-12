package com.group4.ui.SearchActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.Doctor;
import com.group4.data.model.Hospital;
import com.group4.medgo.MainActivity;
import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivitySearchBinding;
import com.group4.ui.common.DoctorAdapter;
import com.group4.ui.common.RecentHospitalAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    private ImageView MicroIcon;
    private ImageView backArrow;
    private RecyclerView recentItemsRecyclerView;
    private RecyclerView recentBSItemsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Đảm bảo setContentView được gọi ngay sau inflate

        // Ánh xạ các View sau khi setContentView
        MicroIcon = binding.MicroIcon;
        // Ánh xạ backArrow từ toolbarLayout (sử dụng cách findViewById trên binding.toolbarLayout)
        backArrow = binding.toolbarLayout.findViewById(R.id.backArrow);
        // Ánh xạ screenTitle trong toolbar (nếu bạn muốn sử dụng nó)
        TextView screenTitle = binding.toolbarLayout.findViewById(R.id.screenTitle);
        if (screenTitle != null) {
            screenTitle.setText("Tìm kiếm"); // Đặt tiêu đề cho màn hình SearchActivity
        }

        recentItemsRecyclerView = binding.recentItemsRecyclerView;
        recentBSItemsRecyclerView = binding.recentBSItemsRecyclerView;

        addEvents();
        setupRecentRecyclerViews();
        setupDoctorRecyclerViews();
    }

    private void setupDoctorRecyclerViews() {
        List<Doctor> recentDoctors = getRecentDoctorsFromDatabase(5);
        recentBSItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        DoctorAdapter doctorAdapter = new DoctorAdapter(this, recentDoctors);
        recentBSItemsRecyclerView.setAdapter(doctorAdapter);
    }

    private List<Doctor> getRecentDoctorsFromDatabase(int limit) {
        List<Doctor> doctors = new ArrayList<>();
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();

            String query = "SELECT d.doctor_id, d.full_name, d.specialization, d.title, d.hospital_id, d.department_id, d.img_doctor, " +
                    "dep.department_name, hos.hospital_name " +
                    "FROM doctors d " +
                    "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                    "LEFT JOIN hospitals hos ON d.hospital_id = hos.hospital_id " +
                    "LIMIT " + limit;

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("doctor_id"));
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
                String specialization = cursor.getString(cursor.getColumnIndexOrThrow("specialization"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                int hospitalId = cursor.getInt(cursor.getColumnIndexOrThrow("hospital_id"));
                int departmentId = cursor.getInt(cursor.getColumnIndexOrThrow("department_id"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("img_doctor"));
                String departmentName = cursor.getString(cursor.getColumnIndexOrThrow("department_name"));
                String hospitalName = cursor.getString(cursor.getColumnIndexOrThrow("hospital_name"));

                Doctor doctor = new Doctor(id, fullName, specialization, title, hospitalId, departmentId, image, hospitalName, departmentName);
                doctors.add(doctor);
                Log.d("SearchActivity", "Doctor loaded: " + fullName);
            }

            cursor.close();
            dbHelper.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SearchActivity", "Lỗi I/O khi mở/tạo database: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SearchActivity", "Lỗi khi load dữ liệu bác sĩ: " + e.getMessage());
        }
        return doctors;
    }

    private void setupRecentRecyclerViews() {
        List<Hospital> topHospitals = getTopHospitalsFromDatabase(2);
        recentItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RecentHospitalAdapter hospitalAdapter = new RecentHospitalAdapter(this, topHospitals);
        recentItemsRecyclerView.setAdapter(hospitalAdapter);
    }

    private List<Hospital> getTopHospitalsFromDatabase(int limit) {
        List<Hospital> hospitals = new ArrayList<>();
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM hospitals LIMIT " + limit, null);
            int count = 0;
            while (cursor.moveToNext() && count < limit) {
                int hospitalId = cursor.getInt(cursor.getColumnIndexOrThrow("hospital_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("hospital_name"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String map = cursor.getString(cursor.getColumnIndexOrThrow("map"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("descreption"));
                boolean supportOnline = cursor.getInt(cursor.getColumnIndexOrThrow("support_online")) == 1;
                String image = cursor.getString(cursor.getColumnIndexOrThrow("img_hospital"));

                Hospital hospital = new Hospital(hospitalId, name, address, phone, map, description, supportOnline, image);
                hospitals.add(hospital);
                count++;

                Log.d("SearchActivity", "Hospital loaded: " + name);
            }

            cursor.close();
            dbHelper.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SearchActivity", "Lỗi I/O khi mở/tạo database: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SearchActivity", "Lỗi khi load dữ liệu bệnh viện: " + e.getMessage());
        }
        return hospitals;
    }

    private void addEvents() {
        if (backArrow != null) { // Kiểm tra null an toàn
            backArrow.setOnClickListener(v -> navigateToMainActivity());
        }
        if (MicroIcon != null) { // Kiểm tra null an toàn
            MicroIcon.setOnClickListener(v -> showSpeakSearchDialog());
        }


        // Thêm Listener cho EditText để bắt sự kiện nhấn Enter
        if (binding.searchEditText != null) { // Kiểm tra null an toàn
            binding.searchEditText.setOnEditorActionListener((v, actionId, event) -> {
                Log.d("SearchActivity", "Editor action: " + actionId + ", event: " + (event != null ? event.getKeyCode() : "null"));
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String query = binding.searchEditText.getText().toString().trim();
                    if (!query.isEmpty()) {
                        Log.d("SearchActivity", "Navigating to SearchKQActivity with query: " + query);
                        navigateToSearchKQActivity(query);
                    } else {
                        Toast.makeText(SearchActivity.this, "Vui lòng nhập từ khóa tìm kiếm.", Toast.LENGTH_SHORT).show();
                    }
                    return true; // Xử lý sự kiện
                }
                return false; // Không xử lý sự kiện
            });
        }
    }

    private void navigateToSearchKQActivity(String query) {
        Intent intent = new Intent(SearchActivity.this, SearchKQActivity.class);
        intent.putExtra("search_query", query); // Gửi từ khóa tìm kiếm
        startActivity(intent);
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showSpeakSearchDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.speaksearch);

        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

        tvTitle.setText("Nói gì đó");
        tvMessage.setText("...");

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnConfirm.setOnClickListener(v -> dialog.dismiss());

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
        }

        dialog.show();
    }
}