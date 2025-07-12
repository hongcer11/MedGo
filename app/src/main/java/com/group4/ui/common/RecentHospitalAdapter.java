package com.group4.ui.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.data.model.Hospital;
import com.group4.medgo.R;
import com.group4.ui.SearchActivity.BVDetailActivity;
import com.group4.ui.SearchActivity.SearchActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RecentHospitalAdapter extends RecyclerView.Adapter<RecentHospitalAdapter.RecentHospitalViewHolder> {
    private Context context;
    private List<Hospital> hospitalList;

    public RecentHospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override

    public RecentHospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recentbvviewitem, parent, false);
        return new RecentHospitalViewHolder(view); // <--- Trả về ViewHolder của RecentHospitalAdapter
    }

    @Override
    // Đảm bảo kiểu holder là RecentHospitalViewHolder
    public void onBindViewHolder(@NonNull RecentHospitalViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);

        holder.hospitalName.setText(hospital.getHospitalName());
        holder.hospitalAddress.setText(hospital.getAddress());

        // Kiểm tra null cho các view có thể không tồn tại trong recentbvviewitem
        if (holder.typeCSYT != null) {
            holder.typeCSYT.setText(hospital.isSupportOnline() ? "Bệnh viện" : "Không hỗ trợ online");
        }
        if (holder.ratingValue != null) {
            holder.ratingValue.setText("4.9");
        }
        if (holder.reviewCount != null) {
            holder.reviewCount.setText("(58 Đánh giá)");
        }

        try {
            InputStream inputStream = context.getAssets().open("images/hospitals/" + hospital.getImgHospital());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.hospitalImage.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            holder.hospitalImage.setImageResource(R.drawable.hospital_placeholder);
        }

        // Click actions
        if (holder.btnViewDetails != null) {
            holder.btnViewDetails.setOnClickListener(v -> {
                // Tạo Intent để mở BVDetailActivity
                Intent intent = new Intent(context, BVDetailActivity.class);
                // Truyền đối tượng Hospital qua Intent
                intent.putExtra("hospital_data", hospital); // "hospital_data" là một key tùy chọn
                context.startActivity(intent);
            });
        }
        if (holder.btnBookAppointment != null) {
            holder.btnBookAppointment.setOnClickListener(v -> {
                Toast.makeText(context, "Đặt lịch khám: " + hospital.getHospitalName(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }


    public static class RecentHospitalViewHolder extends RecyclerView.ViewHolder {

        ImageView hospitalImage, favoriteIcon;
        TextView hospitalName, hospitalAddress, ratingValue, reviewCount, typeCSYT;
        AppCompatButton btnBookAppointment, btnViewDetails;

        public RecentHospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalImage = itemView.findViewById(R.id.hospitalImage);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalAddress = itemView.findViewById(R.id.hospitalAddress);
            ratingValue = itemView.findViewById(R.id.ratingValue);
            reviewCount = itemView.findViewById(R.id.reviewCount);
            typeCSYT = itemView.findViewById(R.id.typeCSYT);
            btnBookAppointment = itemView.findViewById(R.id.btnBookAppointment);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
        }
    }
}