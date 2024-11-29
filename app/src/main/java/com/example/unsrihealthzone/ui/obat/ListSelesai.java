package com.example.unsrihealthzone.ui.obat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unsrihealthzone.R;

import java.util.ArrayList;
import java.util.List;


public class ListSelesai extends Fragment {

    private RecyclerView recyclerView;
    private AdapterSelesai adapter;
    private List<JenisObat> obatList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_selesai, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_selesai);


        // Menginisialisasi daftar obat
        obatList = new ArrayList<>();
        obatList.add(new JenisObat("Paracetamol 500mg", "1 strip", 6000, 0)); // Contoh data

        // Menghubungkan adapter ke RecyclerView
        adapter = new AdapterSelesai(getContext(), obatList, (position, qty) -> updateButtonsVisibility());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);





        return view;
    }

    private void updateButtonsVisibility() {
        // Hitung total quantity obat
        int totalQty = 0;
        for (JenisObat obat : obatList) {
            totalQty += obat.getQty();
        }

        // Tampilkan atau sembunyikan tombol berdasarkan quantity
        boolean isVisible = totalQty > 0;

    }
}