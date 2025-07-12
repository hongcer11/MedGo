package com.group4.ui.booking;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.group4.medgo.R;
import com.group4.medgo.databinding.ActivityBookingFlowBinding;

public class BookingFlowActivity extends AppCompatActivity {
    private ActivityBookingFlowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookingFlowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            goToStep(1);
        }
    }
    public void goToStep(int stepNumber) {
        Fragment fragment;
        switch (stepNumber) {
            case 1:
                fragment = new BookingStep1Fragment();
                break;
            case 2:
                fragment = new BookingStep2Fragment();
                break;
            case 3:
                fragment = new BookingStep3Fragment();
                break;
            case 4:
                fragment = new BookingStep4Fragment();
                break;
            default:
                return;
        }
        updateStepIndicator(stepNumber);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateStepIndicator(int step) {
        int resId;
        switch (step) {
            case 1:
                resId = R.drawable.booking_s1;
                break;
            case 2:
                resId = R.drawable.booking_s2;
                break;
            case 3:
                resId = R.drawable.booking_s3;
                break;
            case 4:
                resId = R.drawable.booking_s4;
                break;
            default:
                resId = R.drawable.booking_s1;
        }
        binding.ivStepIndicator.setImageResource(resId);
    }
}
