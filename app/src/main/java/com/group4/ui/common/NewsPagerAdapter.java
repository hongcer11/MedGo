package com.group4.ui.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.group4.ui.news.NewsTabFragment;

public class NewsPagerAdapter extends FragmentStateAdapter {

    public NewsPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return NewsTabFragment.newInstance("Tổng hợp"); // Tổng hợp
            case 1:
                return NewsTabFragment.newInstance("Tin y tế");  // Y tế
            case 2:
                return NewsTabFragment.newInstance("Mẹo sức khỏe");    // Mẹo sức khỏe
            default:
                return NewsTabFragment.newInstance("Tổng hợp");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
