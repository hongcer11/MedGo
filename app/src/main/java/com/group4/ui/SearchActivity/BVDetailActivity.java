package com.group4.ui.SearchActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group4.data.model.Hospital;
import com.group4.medgo.MainActivity;
import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityBvdetailBinding;

import java.io.IOException;
import java.io.InputStream;

public class BVDetailActivity extends AppCompatActivity {
    private ActivityBvdetailBinding binding; // Khai báo binding
    private Hospital currentHospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBvdetailBinding.inflate(getLayoutInflater()); // Khởi tạo binding
        binding.toolbarLayout.getViewById(R.id.backArrow);
        setContentView(binding.getRoot());
        getData();
        addEvents();
    }

    private void addEvents() {
        binding.toolbarLayout.getViewById(R.id.backArrow).setOnClickListener(v -> navigateToPreActivity());
    }

    private void navigateToPreActivity() {
        Intent intent = new Intent(BVDetailActivity.this, SearchActivity.class);

        startActivity(intent);
        finish();
    }

    private void getData() {
        if (getIntent().hasExtra("hospital_data")) {
            currentHospital = getIntent().getParcelableExtra("hospital_data");
            if (currentHospital != null) {
                displayHospitalDetails(currentHospital);
            } else {
                Toast.makeText(this, "Không tìm thấy dữ liệu bệnh viện!", Toast.LENGTH_SHORT).show();
                finish(); // Đóng Activity nếu không có dữ liệu
            }
        } else {
            Toast.makeText(this, "Không có dữ liệu bệnh viện được truyền!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có dữ liệu
        }
    }

    private void displayHospitalDetails(Hospital hospital) {



        // Load ảnh bệnh viện từ assets
        try {
            InputStream inputStream = getAssets().open("images/hospitals/" + hospital.getImgHospital());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            binding.imvBV.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            binding.imvBV.setImageResource(R.drawable.hospital_placeholder); // Ảnh dự phòng
            Log.e("BVDetailActivity", "Lỗi load ảnh: " + e.getMessage());
        }

        // Gán dữ liệu vào các TextView
        binding.txtBVName.setText(hospital.getHospitalName());
        binding.txtDiachi.setText(hospital.getAddress());
        binding.txtSDT.setText(hospital.getPhone());
        binding.txtMota.setText(hospital.getDescription());

        // Các thông tin khác như rating, review count, chuyên khoa, v.v.
        // Bạn sẽ cần logic để hiển thị danh sách chuyên khoa và các bài đánh giá (Reviews)
        // hiện tại tôi giữ nguyên dữ liệu cứng cho rating và reviewCount như trong adapter
        binding.ratingValue.setText("4.9");
        binding.reviewCount.setText("(58 Đánh giá)");

        // TODO: Thiết lập ListView cho các chuyên khoa (lvCKhoa)
        // Bạn sẽ cần một Adapter riêng cho ListView này và một danh sách dữ liệu chuyên khoa.
        // Ví dụ:
        // List<String> khoaList = getKhoaListForHospital(hospital.getHospitalId()); // Lấy từ DB hoặc danh sách cố định
        // ArrayAdapter<String> khoaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, khoaList);
        // binding.lvCKhoa.setAdapter(khoaAdapter);

        // TODO: Thiết lập RecyclerView cho các đánh giá (recyclerViewReviews)
        // Bạn sẽ cần một Adapter riêng cho các đánh giá và một danh sách dữ liệu đánh giá.
        // Ví dụ:
        // List<Review> reviewList = getReviewsForHospital(hospital.getHospitalId()); // Lấy từ DB hoặc API
        // ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewList);
        // binding.recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));
        // binding.recyclerViewReviews.setAdapter(reviewAdapter);

        // Thiết lập sự kiện cho nút "Chỉ đường"
        binding.btnChiDuong.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng chỉ đường cho " + hospital.getMap(), Toast.LENGTH_SHORT).show();
            // TODO: Mở ứng dụng bản đồ với địa chỉ của bệnh viện
        });

        // Thiết lập sự kiện cho nút "Đặt khám ngay"
        binding.btnBook.setOnClickListener(v -> {
            Toast.makeText(this, "Chuyển sang màn hình đặt khám cho " + hospital.getHospitalName(), Toast.LENGTH_SHORT).show();
            // TODO: Chuyển sang màn hình đặt khám
        });

    }
}