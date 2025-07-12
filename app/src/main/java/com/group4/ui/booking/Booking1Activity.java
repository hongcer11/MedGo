package com.group4.ui.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityBooking1Binding;
public class Booking1Activity extends AppCompatActivity {

    private ActivityBooking1Binding binding;

    // Ghi nhớ lựa chọn (-1 = chưa chọn)
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBooking1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initOptions();
        initContinueButton();
    }

    /** Khởi tạo 3 lựa chọn đặt lịch và gắn vào layoutOptions */
    private void initOptions() {
        addOption(0,
                "Khám chuyên khoa",
                "Khám theo triệu chứng cụ thể (vd: tim mạch, ...)",
                true);
        addOption(1,
                "Khám tổng quát",
                "Khám sức khỏe định kỳ hoặc kiểm tra toàn diện",
                true);
        addOption(2,
                "Tái khám",
                "Lịch hẹn tái khám hoặc dựa trên lịch sử đặt lịch",
                false);
    }

    /** Xử lý nút Tiếp tục */
    private void initContinueButton() {
        // Ban đầu disable vì chưa chọn
        binding.btnTiepTuc.setEnabled(false);
        binding.btnTiepTuc.setAlpha(0.6f);

        binding.btnTiepTuc.setOnClickListener(v -> {
            if (selectedIndex == -1) return;   // Phòng trường hợp edge

            Intent i = new Intent(this, BookingFlowActivity.class);
            i.putExtra("BOOKING_TYPE_INDEX", selectedIndex);
            startActivity(i);
        });
    }

    /** Inflate item_radio_option.xml, cài đặt text / trạng thái & xử lý click */
    private void addOption(int index, String title, String subtitle, boolean enabled) {
        View item = LayoutInflater.from(this)
                .inflate(R.layout.item_radio_option, binding.layoutOptions, false);

        TextView tvTitle = item.findViewById(R.id.tvTitle);
        TextView tvSubtitle = item.findViewById(R.id.tvSubtitle);
        ImageView ivIcon = item.findViewById(R.id.ivRadioIcon);
        View container = item.findViewById(R.id.layoutOption);

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);

        // Enable / disable linh hoạt
        container.setEnabled(enabled);
        float alpha = enabled ? 1f : 0.7f;
        tvTitle.setAlpha(alpha);
        tvSubtitle.setAlpha(alpha);
        ivIcon.setAlpha(alpha);
        ivIcon.setImageResource(enabled ? R.drawable.radio_unchecked : R.drawable.radio_disabled);

        container.setOnClickListener(v -> {
            if (!enabled) return;

            // Reset mọi icon
            for (int i = 0; i < binding.layoutOptions.getChildCount(); i++) {
                ImageView icon = binding.layoutOptions.getChildAt(i)
                        .findViewById(R.id.ivRadioIcon);
                View ctn = binding.layoutOptions.getChildAt(i)
                        .findViewById(R.id.layoutOption);
                if (ctn.isEnabled()) icon.setImageResource(R.drawable.radio_unchecked);
            }

            // Chọn icon hiện tại
            ivIcon.setImageResource(R.drawable.radio_checked);

            // Lưu vị trí đã chọn & bật nút Tiếp tục
            selectedIndex = index;
            binding.btnTiepTuc.setEnabled(true);
            binding.btnTiepTuc.setAlpha(1f);
        });

        binding.layoutOptions.addView(item);
    }
}


//public class Booking1Activity extends AppCompatActivity {
//
//    private ActivityBooking1Binding binding;
//    // Ghi nhớ lựa chọn (-1 = chưa chọn)
//    private int selectedIndex = -1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityBooking1Binding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        initOptions();
//        initContinueButton();
//    }
//
//    private void initContinueButton() {
//        // Ban đầu disable vì chưa chọn
//        binding.btnTiepTuc.setEnabled(false);
//        binding.btnTiepTuc.setAlpha(0.6f);
//
//        binding.btnTiepTuc.setOnClickListener(v -> {
//            if (selectedIndex == -1) return;   // Phòng trường hợp edge
//
//            Intent i = new Intent(this, BookingFlowActivity.class);
//            i.putExtra("BOOKING_TYPE_INDEX", selectedIndex);
//            startActivity(i);
//        });
//    }
//
//    /**
//     * Khởi tạo 3 lựa chọn đặt lịch và gắn vào layoutOptions
//     */
//    private void initOptions() {
//        addOption("Khám chuyên khoa", "Khám theo triệu chứng cụ thể (vd: tim mạch, ...)", true);
//        addOption("Khám tổng quát", "Khám sức khỏe định kỳ hoặc kiểm tra toàn diện", true);
//        addOption("Tái khám", "Lịch hẹn tái khám hoặc dựa trên lịch sử đặt lịch", false);
//    }
//
//    /**
//     * Inflate item_radio_option.xml, cài đặt text / trạng thái & xử lý click
//     */
//    private void addOption(String title, String subtitle, boolean enabled) {
//        // Inflate
//        View item = LayoutInflater.from(this).inflate(R.layout.item_radio_option, binding.layoutOptions, false);
//
//        TextView tvTitle = item.findViewById(R.id.tvTitle);
//        TextView tvSubtitle = item.findViewById(R.id.tvSubtitle);
//        ImageView ivIcon = item.findViewById(R.id.ivRadioIcon);
//        View container = item.findViewById(R.id.layoutOption);
//
//        // Set text
//        tvTitle.setText(title);
//        tvSubtitle.setText(subtitle);
//
//        // Trạng thái enable/disable
//        container.setEnabled(enabled);
//        float alpha = enabled ? 1f : 0.7f;
//        tvTitle.setAlpha(alpha);
//        tvSubtitle.setAlpha(alpha);
//        ivIcon.setAlpha(alpha);
//        ivIcon.setImageResource(enabled ? R.drawable.radio_unchecked : R.drawable.radio_disabled);
//
//        // Click chọn
//        container.setOnClickListener(v -> {
//            if (!enabled) return;
//            // Bỏ chọn tất cả icon
//            for (int i = 0; i < binding.layoutOptions.getChildCount(); i++) {
//                ImageView icon = binding.layoutOptions.getChildAt(i).findViewById(R.id.ivRadioIcon);
//                View ctn = binding.layoutOptions.getChildAt(i).findViewById(R.id.layoutOption);
//                if (ctn.isEnabled()) icon.setImageResource(R.drawable.radio_unchecked);
//            }
//            // Chọn icon hiện tại
//            ivIcon.setImageResource(R.drawable.radio_checked);
//        });
//
//        // Thêm vào LinearLayout
//        binding.layoutOptions.addView(item);
//    }
//}