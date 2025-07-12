package com.group4.ui.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.models.Appointment;
import com.group4.medgo.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

<<<<<<< HEAD

=======
>>>>>>> a70cdef (Resolved all merge conflicts)
    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        holder.tvCode.setText("Mã phiếu: " + appointment.getCode());
        holder.tvName.setText(appointment.getName());
        holder.tvHospital.setText(appointment.getHospital());
        holder.tvSpecialty.setText(appointment.getSpecialty());
        holder.tvService.setText(appointment.getService());
        holder.tvStatus.setText(appointment.getStatus());
        holder.tvDate.setText(appointment.getDate());
        holder.tvTime.setText(appointment.getTime());
    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

=======
        return appointmentList != null ? appointmentList.size() : 0;
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
>>>>>>> a70cdef (Resolved all merge conflicts)
        TextView tvCode, tvName, tvHospital, tvSpecialty, tvService, tvStatus, tvDate, tvTime;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHospital = itemView.findViewById(R.id.tv_hospital);
            tvSpecialty = itemView.findViewById(R.id.tv_specialty);
            tvService = itemView.findViewById(R.id.tv_service);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
