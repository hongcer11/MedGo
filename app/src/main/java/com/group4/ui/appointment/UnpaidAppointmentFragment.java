package com.group4.ui.appointment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.models.Appointment;
import com.group4.medgo.R;
import com.group4.ui.common.AppointmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class UnpaidAppointmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private List<Appointment> appointmentList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unpaid_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerUnpaid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment("AI23456789", "ĐINH HỒNG CƠ", "BỆNH VIỆN DA LIỄU TP.HCM",
                "Khoa da liễu", "Khám dịch vụ", "Chưa thanh toán", "13/06/2025", "14:15"));

        adapter = new AppointmentAdapter(appointmentList);
        recyclerView.setAdapter(adapter);
    }
}


