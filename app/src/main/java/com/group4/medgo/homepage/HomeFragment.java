package com.group4.medgo.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.medgo.R;
import com.group4.medgo.databinding.FragmentHomeBinding;
import com.group4.models.Partner;
import com.group4.ui.common.PartnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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


        binding.recyclerNews.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        return binding.getRoot();
    }
}