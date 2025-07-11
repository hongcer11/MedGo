package com.group4.ui.SearchActivity;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.Doctor;
import com.group4.medgo.R;
import com.group4.ui.common.DoctorAdapter;

import java.util.ArrayList;
import java.util.List;
public class DoctorKQFragment extends Fragment {

    private static final String ARG_QUERY = "search_query";
    private String searchQuery;
    private ListView listView;
    private DoctorAdapter adapter;  // Adapter dùng cho ListView
    private List<Doctor> doctorList;
    private TextView tvNoResults;

    public static DoctorKQFragment newInstance(String query) {
        DoctorKQFragment fragment = new DoctorKQFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchQuery = getArguments().getString(ARG_QUERY);
        }
        doctorList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_k_q, container, false);
        listView = view.findViewById(R.id.ViewDoctorKQ);
        tvNoResults = view.findViewById(R.id.tvNoResults);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDoctorResults();
    }

    private void loadDoctorResults() {
        doctorList.clear();
        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());

        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();

            String query = "SELECT d.doctor_id, d.full_name, d.specialization, d.title, d.hospital_id, d.department_id, d.img_doctor, " +
                    "dep.department_name, hos.hospital_name " +
                    "FROM doctors d " +
                    "LEFT JOIN departments dep ON d.department_id = dep.department_id " +
                    "LEFT JOIN hospitals hos ON d.hospital_id = hos.hospital_id " +
                    "WHERE d.full_name LIKE ? OR d.specialization LIKE ? OR dep.department_name LIKE ? OR hos.hospital_name LIKE ?";

            String likeQuery = "%" + searchQuery + "%";
            Cursor cursor = db.rawQuery(query, new String[]{likeQuery, likeQuery, likeQuery, likeQuery});

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
                doctorList.add(doctor);
            }

            cursor.close();
            dbHelper.close();

            if (doctorList.isEmpty()) {
                tvNoResults.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                tvNoResults.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                adapter = new DoctorAdapter(getContext(), doctorList);
                listView.setAdapter((ListAdapter) adapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DoctorKQFragment", "Lỗi khi load dữ liệu bác sĩ: " + e.getMessage());
        }
    }
}