package com.group4.ui.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.medgo.R;
import com.group4.data.model.Doctor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private List<Doctor> doctorList;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.txtFullName.setText(doctor.getTitle() + " " + doctor.getFullName());
        holder.txtSpecialization.setText("Khoa " + doctor.getSpecialization());
        holder.txtHospital.setText("Bệnh viện Da liễu TP.HCM");

        try {
            InputStream inputStream = context.getAssets().open("images/doctors/" + doctor.getImgDoctor());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.imgDoctor.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            holder.imgDoctor.setImageResource(R.drawable.dang_ngoc_khanh); // fallback ảnh mặc định
        }
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView txtFullName, txtSpecialization, txtHospital;
        ImageView imgDoctor;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtSpecialization = itemView.findViewById(R.id.txtSpecialization);
            txtHospital = itemView.findViewById(R.id.txtHospital);
            imgDoctor = itemView.findViewById(R.id.imgDoctor);
        }
    }
}
