package com.group4.ui.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.data.model.Hospital;
import com.group4.medgo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    private Context context;
    private List<Hospital> hospitalList;

    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bv_item, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);

        holder.hospitalName.setText(hospital.getHospitalName());
        holder.hospitalAddress.setText(hospital.getAddress());
        holder.typeCSYT.setText(hospital.isSupportOnline() ? "Bệnh viện" : "Không hỗ trợ online");

        // Nếu bạn có số sao và đánh giá thì truyền, hiện tạm mặc định
        holder.ratingValue.setText("4.9");
        holder.reviewCount.setText("(58 Đánh giá)");

        try {
            InputStream inputStream = context.getAssets().open("images/hospitals/" + hospital.getImgHospital());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.hospitalImage.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            holder.hospitalImage.setImageResource(R.drawable.hospital_placeholder);
        }

        // Click actions (bạn có thể truyền callback nếu cần)
        holder.btnViewDetails.setOnClickListener(v -> {
            // Mở trang chi tiết bệnh viện
        });

        holder.btnBookAppointment.setOnClickListener(v -> {
            // Chuyển sang màn đặt khám
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public static class HospitalViewHolder extends RecyclerView.ViewHolder {

        ImageView hospitalImage, favoriteIcon;
        TextView hospitalName, hospitalAddress, ratingValue, reviewCount, typeCSYT;
        AppCompatButton btnBookAppointment, btnViewDetails;

        public HospitalViewHolder(@NonNull View itemView) {
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
