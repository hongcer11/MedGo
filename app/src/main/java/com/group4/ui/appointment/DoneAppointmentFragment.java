package com.group4.ui.appointment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.medgo.R;

public class DoneAppointmentFragment extends Fragment {

    public DoneAppointmentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_done_appointment, container, false);
    }
}
