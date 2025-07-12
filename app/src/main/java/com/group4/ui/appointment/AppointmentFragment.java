package com.group4.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.group4.medgo.R;

public class AppointmentFragment extends Fragment {

    private Fragment currentFragment;
    private Button btnPaid, btnUnpaid, btnDone, btnCancelled;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        btnPaid = view.findViewById(R.id.btn_paid);
        btnUnpaid = view.findViewById(R.id.btn_unpaid);
        btnDone = view.findViewById(R.id.btn_done);
        btnCancelled = view.findViewById(R.id.btn_cancelled);

        // Mặc định hiển thị tab "Chưa thanh toán"
        switchFragment(new UnpaidAppointmentFragment());

        btnPaid.setOnClickListener(v -> switchFragment(new PaidAppointmentFragment()));
        btnUnpaid.setOnClickListener(v -> switchFragment(new UnpaidAppointmentFragment()));
        btnDone.setOnClickListener(v -> switchFragment(new DoneAppointmentFragment()));
        btnCancelled.setOnClickListener(v -> switchFragment(new CancelAppointmentFragment()));

        return view;
    }

    private void switchFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            currentFragment = fragment;
            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.appointment_tab_container, fragment);
            ft.commit();
        }
    }
}
