package com.group4.medgo.homepage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group4.medgo.R;
import com.group4.medgo.databinding.FragmentChatbotBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatbotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatbotFragment extends Fragment {

    FragmentChatbotBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatbotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatbotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatbotFragment newInstance(String param1, String param2) {
        ChatbotFragment fragment = new ChatbotFragment();
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
        binding = FragmentChatbotBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ẩn bottomAppBar vs Navbar vs fabDatlich khi mở Chatbot Fragment
        View bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        if (bottomAppBar != null) {
            bottomAppBar.setVisibility(View.GONE);
        }
        View fabDatlich = requireActivity().findViewById(R.id.fabDatlich);
        if (fabDatlich != null) {
            fabDatlich.setVisibility(View.GONE);
        }

        // Nút X để thoát ChatbotFragment
        binding.btnClose.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
            requireActivity().findViewById(R.id.bottomAppBar).setVisibility(View.VISIBLE);
            requireActivity().findViewById(R.id.fabDatlich).setVisibility(View.VISIBLE);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}