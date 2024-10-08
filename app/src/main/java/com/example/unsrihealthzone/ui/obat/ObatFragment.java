package com.example.unsrihealthzone.ui.obat;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.ui.ambulans.CallingAmbulance;

public class ObatFragment extends Fragment {
    private FrameLayout telepon;
    private ObatViewModel mViewModel;

    public static ObatFragment newInstance() {
        return new ObatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_obat, container, false);

        // Inisialisasi FrameLayout di sini menggunakan view
        telepon = view.findViewById(R.id.telepon);

        // Set onClickListener untuk FrameLayout
        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke Activity CallingAmbulance
                Intent intent = new Intent(getActivity(), CallingAmbulance.class);
                startActivity(intent);
            }
        });

        return view; // Kembalikan view di akhir
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ObatViewModel.class);
        // TODO: Use the ViewModel
    }
}
