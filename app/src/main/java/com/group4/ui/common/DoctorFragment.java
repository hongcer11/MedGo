package com.group4.ui.common;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.Doctor;
import com.group4.medgo.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends Fragment {

    private RecyclerView recyclerDoctor;
    private DoctorAdapter doctorAdapter;
    private List<Doctor> doctorList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        recyclerDoctor = view.findViewById(R.id.recyclerDoctor);
        recyclerDoctor.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDoctorsFromDatabase();
    }
    private void loadDoctorsFromDatabase() {
        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());
        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Doctors", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("doctor_id"));
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
                String specialization = cursor.getString(cursor.getColumnIndexOrThrow("specialization"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                int hospitalId = cursor.getInt(cursor.getColumnIndexOrThrow("hospital_id"));
                int departmentId = cursor.getInt(cursor.getColumnIndexOrThrow("department_id"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("img_doctor"));
                Doctor doctor = new Doctor(id, fullName, specialization, title, hospitalId, departmentId, image);
                doctorList.add(doctor);
                Log.d("DoctorFragment", "Doctor loaded: " + fullName);
            }

            cursor.close();
            dbHelper.close();

            doctorAdapter = new DoctorAdapter(getContext(), doctorList);
            recyclerDoctor.setAdapter(doctorAdapter);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DoctorFragment", "Lỗi khi load dữ liệu bác sĩ: " + e.getMessage());
        }
    }
}
