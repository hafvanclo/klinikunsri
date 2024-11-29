package com.example.unsrihealthzone.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.unsrihealthzone.ProfileManagement;
import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.databinding.FragmentDashboardBinding;
import com.example.unsrihealthzone.ui.ambulans.SendLokasi;
import com.example.unsrihealthzone.ui.ambulans.popup_darurat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TextView timeTextView;
    private Handler handler = new Handler();
    private Runnable runnable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get reference to your TextView
        timeTextView = root.findViewById(R.id.text_jam);

        // Set the time
        updateTime();

        // Update the time every minute
        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 60000); // 60000 ms = 1 minute
            }
        };
        handler.post(runnable);


        ImageView imageView9 = root.findViewById(R.id.imageProfile);
        FrameLayout imageView8 = root.findViewById(R.id.darurat);
        FrameLayout dokter = root.findViewById(R.id.dokter);
        FrameLayout obat = root.findViewById(R.id.obat);
        FrameLayout layanan = root.findViewById(R.id.layanan);

        imageView9.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileManagement.class);
            startActivity(intent);
        });


        dokter.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_dashboard); // Ensure the ID is correct
            navController.navigate(R.id.navigation_dokter);
        });

        obat.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_dashboard); // Ensure the ID is correct
            navController.navigate(R.id.navigation_call);
        });

        layanan.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_dashboard); // Ensure the ID is correct
            navController.navigate(R.id.navigation_obat);
        });
        imageView8.setOnClickListener(v -> {

            PopUpDaruratMenu popup = new PopUpDaruratMenu();
            popup.show(getParentFragmentManager(), "popup_darurat");
        });
        return root;
    }

    private void updateTime() {
        // Get the current time in WIB (UTC+7)
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta")); // Set timezone to Jakarta

        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        String currentTime = dateFormat.format(calendar.getTime());

        // Update the TextView with the current time
        timeTextView.setText(currentTime);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable); // Stop the handler when the fragment is destroyed
        binding = null;
    }
}