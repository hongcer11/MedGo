package com.group4.ui.SearchActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivitySearchKqactivityBinding;

public class SearchKQActivity extends AppCompatActivity {
    private ActivitySearchKqactivityBinding binding;
    private String currentSearchQuery;
    private TextView tabCsyt, tabBacSi, tabChuyenKhoa;
    private EditText searchEditText;
    private ImageView clearTextIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchKqactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ánh xạ các View
        searchEditText = binding.searchBarContainer.findViewById(R.id.searchEditText);
        clearTextIcon = binding.searchBarContainer.findViewById(R.id.clearTextIcon);

        tabCsyt = binding.customTabLayout.findViewById(R.id.tabCsyt);
        tabBacSi = binding.customTabLayout.findViewById(R.id.tabBacSi);
        tabChuyenKhoa = binding.customTabLayout.findViewById(R.id.tabChuyenKhoa);

        // Xử lý Intent để lấy từ khóa tìm kiếm
        if (getIntent().hasExtra("search_query")) {
            currentSearchQuery = getIntent().getStringExtra("search_query");
            searchEditText.setText(currentSearchQuery); // Hiển thị từ khóa lên EditText
            searchEditText.setSelection(currentSearchQuery.length()); // Di chuyển con trỏ về cuối
        } else {
            currentSearchQuery = "";
        }

        addEvents(); // Thêm sự kiện cho các View khác
        setupTabs(); // Thiết lập chức năng cho các Tab

        // Mặc định hiển thị tab CSYT khi mới vào
        selectTab(tabCsyt); // Chỉ gọi selectTab để hiển thị Fragment ban đầu
    }

    private void addEvents() {
        // Listener cho nút xóa văn bản trong EditText
        clearTextIcon.setOnClickListener(v -> searchEditText.setText(""));

        // Listener cho sự thay đổi văn bản trong EditText để ẩn/hiện nút xóa
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearTextIcon.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Listener cho sự kiện nhấn Enter trên EditText để tìm kiếm lại
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String newQuery = searchEditText.getText().toString().trim();
                if (!newQuery.isEmpty()) {
                    currentSearchQuery = newQuery; // Cập nhật từ khóa tìm kiếm
                    // Tải lại Fragment hiện tại với từ khóa mới
                    reloadCurrentFragment();
                } else {
                    Toast.makeText(SearchKQActivity.this, "Vui lòng nhập từ khóa tìm kiếm.", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
    }

    private void setupTabs() {
        // --- Sửa lỗi ở đây: Di chuyển logic setSelected vào trong OnClickListener của từng tab ---
        tabCsyt.setOnClickListener(v -> {
            // Đặt trạng thái 'selected' cho tab này
            v.setSelected(true);
            // Bỏ trạng thái 'selected' của các tab khác
            tabBacSi.setSelected(false);
            tabChuyenKhoa.setSelected(false);
            // Hiển thị Fragment tương ứng
            selectTab(tabCsyt);
        });

        tabBacSi.setOnClickListener(v -> {
            // Đặt trạng thái 'selected' cho tab này
            v.setSelected(true);
            // Bỏ trạng thái 'selected' của các tab khác
            tabCsyt.setSelected(false);
            tabChuyenKhoa.setSelected(false);
            // Hiển thị Fragment tương ứng
            selectTab(tabBacSi);
        });

        tabChuyenKhoa.setOnClickListener(v -> {
            // Đặt trạng thái 'selected' cho tab này
            v.setSelected(true);
            // Bỏ trạng thái 'selected' của các tab khác
            tabCsyt.setSelected(false);
            tabBacSi.setSelected(false);
            // Hiển thị Fragment tương ứng
            selectTab(tabChuyenKhoa);
        });
        // --- Kết thúc sửa lỗi ---
    }

    private void selectTab(TextView selectedTab) {
        // Hàm này chỉ có trách nhiệm thay thế Fragment, không thay đổi trạng thái của tab
        Fragment fragment = null;
        if (selectedTab.getId() == R.id.tabCsyt) {
            fragment = HospitalKQFragment.newInstance(currentSearchQuery);
        } else if (selectedTab.getId() == R.id.tabBacSi) {
            fragment = DoctorKQFragment.newInstance(currentSearchQuery);
        } else if (selectedTab.getId() == R.id.tabChuyenKhoa) {
            fragment = DepartmentKQFragment.newInstance("Chưa được triển khai");
            // Hoặc có thể truyền currentSearchQuery nếu DepartmentKQFragment cũng cần nó
            // fragment = DepartmentKQFragment.newInstance(currentSearchQuery);
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    private void reloadCurrentFragment() {
        // Lấy Fragment đang hiển thị
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (currentFragment instanceof HospitalKQFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, HospitalKQFragment.newInstance(currentSearchQuery))
                    .commit();
            // Đảm bảo tab được chọn vẫn sáng sau khi reload
            tabCsyt.setSelected(true);
            tabBacSi.setSelected(false);
            tabChuyenKhoa.setSelected(false);
        } else if (currentFragment instanceof DoctorKQFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, DoctorKQFragment.newInstance(currentSearchQuery))
                    .commit();
            tabCsyt.setSelected(false);
            tabBacSi.setSelected(true);
            tabChuyenKhoa.setSelected(false);
        } else if (currentFragment instanceof DepartmentKQFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, DepartmentKQFragment.newInstance(currentSearchQuery))
                    .commit();
            tabCsyt.setSelected(false);
            tabBacSi.setSelected(false);
            tabChuyenKhoa.setSelected(true);
        } else {
            // Nếu không có Fragment nào đang hiển thị, hoặc không xác định được, mặc định về CSYT
            tabCsyt.setSelected(true);
            tabBacSi.setSelected(false);
            tabChuyenKhoa.setSelected(false);
            selectTab(tabCsyt);
        }
    }
}