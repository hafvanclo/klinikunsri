package com.example.unsrihealthzone.ui.ambulans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.databinding.FragmentAmbulansBinding;

public class AmbulansFragment extends Fragment {

    private FragmentAmbulansBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAmbulansBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AmbulansViewModel ambulansViewModel =
                new ViewModelProvider(this).get(AmbulansViewModel.class);

        ImageView imageView8 = root.findViewById(R.id.imageView8);


        imageView8.setOnClickListener(v -> {
            // Tampilkan popup sebagai dialog
            popup_darurat popup = new popup_darurat();
            popup.show(getParentFragmentManager(), "popup_darurat");
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}