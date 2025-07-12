package com.group4.ui.SearchActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group4.data.db.SQLiteHelper;
import com.group4.data.model.Hospital;
import com.group4.medgo.R;
import com.group4.ui.common.HospitalAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HospitalKQFragment extends Fragment {

    private static final String ARG_QUERY = "search_query";
    private String searchQuery;
    private ListView listView; // Dùng ListView thay cho RecyclerView
    private HospitalAdapter adapter;
    private List<Hospital> hospitalList;
    private TextView tvNoResults;

    public HospitalKQFragment() {
        // Required empty public constructor
    }

    public static HospitalKQFragment newInstance(String query) {
        HospitalKQFragment fragment = new HospitalKQFragment();
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
        hospitalList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital_k_q, container, false);

        tvNoResults = view.findViewById(R.id.tvNoResults);
        listView = view.findViewById(R.id.ViewHospitalKQ); // ánh xạ ListView theo ID bạn đã cung cấp
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadHospitalResults();
    }

    private void loadHospitalResults() {
        hospitalList.clear();
        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());
        try {
            dbHelper.createDatabaseIfNeeded();
            dbHelper.openDatabase();
            SQLiteDatabase db = dbHelper.getDatabase();

            String query = "SELECT * FROM hospitals WHERE hospital_name LIKE ? OR address LIKE ?";
            Cursor cursor = db.rawQuery(query, new String[]{"%" + searchQuery + "%", "%" + searchQuery + "%"});

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
            }
            cursor.close();
            dbHelper.close();

            if (hospitalList.isEmpty()) {
                tvNoResults.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                tvNoResults.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                adapter = new HospitalAdapter(getContext(), hospitalList);
                listView.setAdapter((ListAdapter) adapter);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HospitalKQFragment", "Lỗi I/O khi mở/tạo database: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HospitalKQFragment", "Lỗi khi load dữ liệu bệnh viện: " + e.getMessage());
        }
    }
}
