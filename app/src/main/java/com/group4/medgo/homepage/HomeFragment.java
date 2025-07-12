package com.group4.medgo.homepage;

import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.group4.data.model.Partner;
import com.group4.data.model.User;
import com.group4.medgo.MainActivity;
import com.group4.medgo.R;
import com.group4.medgo.databinding.FragmentHomeBinding;
import com.group4.ui.common.PartnerAdapter;

import java.util.ArrayList;

import com.group4.ui.SearchActivity.SearchActivity;
import com.group4.ui.news.NewsListFragment;
import com.group4.ui.user.LoginPasswordActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ConstraintLayout searchBarContainer;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentHomeBinding binding;
    PartnerAdapter adapter;
    ArrayList<Partner> partners;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        partners = new ArrayList<>();
        partners.add(new Partner(R.drawable.bv_choray, "Bệnh viện Chợ Rẫy"));
        partners.add(new Partner(R.drawable.bv_tudu, "Bệnh viện Từ Dũ"));
        partners.add(new Partner(R.drawable.bv_dalieu, "Bệnh viện Da liễu TP.HCM"));
        partners.add(new Partner(R.drawable.bv_yduoc, "Bệnh viện Y dược TP.HCM"));
        partners.add(new Partner(R.drawable.bv_ungbuou, "Bệnh viện Ung bướu TP.HCM"));
        partners.add(new Partner(R.drawable.bv_nhandan, "Bệnh viện Nhân dân 115"));
        partners.add(new Partner(R.drawable.bv_nhidong1, "Bệnh viện Nhi Đồng 1"));
        partners.add(new Partner(R.drawable.bv_chanthuongchinhhinh, "Bệnh viện Chấn thương chỉnh hình"));
        partners.add(new Partner(R.drawable.bv_hoanmythuduc, "Bệnh viện Quốc tế Hoàn Mỹ Thủ Đức"));

        binding.recyclerPartner.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        adapter = new PartnerAdapter(getContext(), partners);
        binding.recyclerPartner.setAdapter(adapter);

        // Xử lý khi bấm vào chữ "Đăng nhập"
        binding.tvlogin.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), LoginPasswordActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Tìm searchBarContainer theo ID
        // Sử dụng view.findViewById để tìm view trong layout của fragment
        searchBarContainer = view.findViewById(R.id.searchBarContainer);
        View searchEditText = view.findViewById(R.id.searchEditText);
        // 2. Thiết lập OnClickListener
        if (searchBarContainer != null) {
            searchBarContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để chuyển sang SearchActivity
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (searchEditText != null) {
            searchEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để chuyển sang SearchActivity
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }
            });
        }
        // Xem tin tức
        View newsContainer = view.findViewById(R.id.newsContainer);
        View btnNewsMore = view.findViewById(R.id.btnNewsMore);
        View newsImage = view.findViewById(R.id.newsImage);

        View.OnClickListener newsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang NewsListFragment
                Fragment newsListFragment = new NewsListFragment();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, newsListFragment) // Sử dụng đúng ID vùng chứa
                        .addToBackStack(null) // Cho phép quay lại bằng nút back
                        .commit();
            }
        };
        newsContainer.setOnClickListener(newsClickListener);
        btnNewsMore.setOnClickListener(newsClickListener);
        newsImage.setOnClickListener(newsClickListener);

        binding.btnChatbot.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            View sheetView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.contacthelp_dialog, null);

            TextView optionCall = sheetView.findViewById(R.id.optionCall);
            TextView optionChat = sheetView.findViewById(R.id.optionChatbot);
            ImageView btnClose = sheetView.findViewById(R.id.btnClose);

            optionCall.setOnClickListener(view1 -> {
                bottomSheetDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:19001234"));
                startActivity(intent);
            });

            optionChat.setOnClickListener(view2 -> {
                bottomSheetDialog.dismiss();
                // Mở ChatbotFragment
                Fragment chatbotFragment = new ChatbotFragment(); // Đảm bảo bạn đã tạo class này
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, chatbotFragment)
                        .addToBackStack(null)
                        .commit();
            });
            btnClose.setOnClickListener(view3 -> bottomSheetDialog.dismiss());

            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
        if (MainActivity.currentUser != null) {
            String fullName = MainActivity.currentUser.getFullName().trim();
            String[] parts = fullName.split(" ");

            String displayName = parts.length >= 2
                    ? parts[parts.length - 2] + " " + parts[parts.length - 1]
                    : parts[parts.length - 1]; // fallback nếu chỉ có 1 từ

            binding.tvlogin.setText(displayName);
            binding.tvlogin.setOnClickListener(null); // không click được nữa
        } else {
            binding.tvlogin.setText("Đăng ký / Đăng nhập");
            binding.tvlogin.setOnClickListener(v -> {
                Intent intent = new Intent(requireActivity(), LoginPasswordActivity.class);
                startActivity(intent);
            });
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}