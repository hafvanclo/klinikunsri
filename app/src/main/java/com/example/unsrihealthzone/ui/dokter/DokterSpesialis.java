package com.example.unsrihealthzone.ui.dokter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unsrihealthzone.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DokterSpesialis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DokterSpesialis extends Fragment {
    private RecyclerView recyclerView;
    private CircleAdapter circleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dokter_spesialis, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        // Menggunakan GridLayoutManager untuk 4 kolom
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        // Daftar ikon (ganti dengan ikon Anda)
        List<Integer> icons = Arrays.asList(
                R.drawable.icon1,
                R.drawable.icon2,
                R.drawable.icon3,
                R.drawable.icon4,
                R.drawable.icon5,
                R.drawable.icon6,
                R.drawable.icon7,
                R.drawable.icon8,
                R.drawable.icon9,
                R.drawable.icon10,
                R.drawable.icon11
        );
        List<String> poliNames = Arrays.asList(
                "Umum",
                "Anak",
                "Kulit",
                "Lambung",
                "Ortopedi",
                "Gigi",
                "Syaraf",
                "Otak",
                "Kandungan",
                "Jiwa",
                "Paru"
        );


        circleAdapter = new CircleAdapter(icons, poliNames);
        recyclerView.setAdapter(circleAdapter);


            // Ganti fragment di FrameLayout
        Button pilihSpesialis = view.findViewById(R.id.pilihspesialis);
        pilihSpesialis.setOnClickListener(v -> replaceFragment(new ListDoktorSpesialis()));

        return view;
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager(); // Jika di dalam Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment); // Ganti dengan ID FrameLayout Anda
        fragmentTransaction.addToBackStack(null); // Menambahkan ke back stack (opsional)
        fragmentTransaction.commit();
    }
}