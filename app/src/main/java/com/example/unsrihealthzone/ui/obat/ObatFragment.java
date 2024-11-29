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
import android.widget.ImageView;

import com.example.unsrihealthzone.Chating;
import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.ui.ambulans.CallingAmbulance;
import com.example.unsrihealthzone.ui.call.CallingLayanan;

public class ObatFragment extends Fragment {
    private FrameLayout telepon;
    private FrameLayout chat;
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
        chat = view.findViewById(R.id.chat);


        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke Activity CallingAmbulance
                Intent intent = new Intent(getActivity(), CallingLayanan.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke Activity CallingAmbulance
                Intent intent = new Intent(getActivity(), Chating.class);
                startActivity(intent);
            }
        });
        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi kembali ke halaman sebelumnya
                requireActivity().onBackPressed();
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
