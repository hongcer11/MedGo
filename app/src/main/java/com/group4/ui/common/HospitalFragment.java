package com.group4.ui.common;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.Hospital;
import com.group4.medgo.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalFragment extends Fragment {

    private RecyclerView recyclerHospital;
    private HospitalAdapter hospitalAdapter;
    private List<Hospital> hospitalList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        recyclerHospital = view.findViewById(R.id.recyclerHospital);
        recyclerHospital.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadHospitalsFromDatabase();
    }

    private void loadHospitalsFromDatabase() {
        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());
        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM hospitals", null);
            while (cursor.moveToNext()) {
                int hospitalId = cursor.getInt(cursor.getColumnIndexOrThrow("hospital_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("hospital_name"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String map = cursor.getString(cursor.getColumnIndexOrThrow("map"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("descreption"));
                boolean supportOnline = cursor.getInt(cursor.getColumnIndexOrThrow("support_online")) == 1;
                String image = cursor.getString(cursor.getColumnIndexOrThrow("img_hospital"));

                Hospital hospital = new Hospital(hospitalId, name, address, phone, map, description, supportOnline, image);
                hospitalList.add(hospital);

                Log.d("HospitalFragment", "Hospital loaded: " + name);
            }

            cursor.close();
            dbHelper.close();

            hospitalAdapter = new HospitalAdapter(getContext(), hospitalList);
            recyclerHospital.setAdapter(hospitalAdapter);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HospitalFragment", "Lỗi khi load dữ liệu bệnh viện: " + e.getMessage());
        }
    }
}
