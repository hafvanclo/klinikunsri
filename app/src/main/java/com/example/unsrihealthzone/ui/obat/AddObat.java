package com.example.unsrihealthzone.ui.obat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unsrihealthzone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddObat extends Fragment {
    private RecyclerView recyclerView;
    private AdapterObat adapter;
    private List<JenisObat> obatList;
    private Button buttonPembayaran, buttonLanjutPilihObat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_obat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_obat);
        buttonPembayaran = view.findViewById(R.id.button_pembayaran);
        buttonLanjutPilihObat = view.findViewById(R.id.button_lanjut_pilih_obat);

        // Inisialisasi daftar obat
        obatList = new ArrayList<>();
        obatList.add(new JenisObat("Paracetamol 500mg", "1 strip", 6000, 0));


        // Atur adapter untuk RecyclerView
        adapter = new AdapterObat(getContext(), obatList, (position, qty) -> updateButtonsVisibility());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Logika tombol pembayaran
        buttonPembayaran.setOnClickListener(v -> saveObatToFirebase());

        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }

    private void saveObatToFirebase() {
        List<HashMap<String, Object>> obatDetails = new ArrayList<>();
        for (JenisObat obat : obatList) {
            if (obat.getQty() > 0) { // Simpan hanya jika jumlah > 0
                HashMap<String, Object> obatData = new HashMap<>();
                obatData.put("name", obat.getNama());
                obatData.put("description", obat.getDeskripsi());
                obatData.put("quantity", obat.getQty());
                obatData.put("price", obat.getHarga());
                obatDetails.add(obatData);
            }
        }

        if (!obatDetails.isEmpty()) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("obatDetails");
            String key = databaseReference.push().getKey();

            if (key != null) {
                databaseReference.child(key).setValue(obatDetails).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Data obat berhasil disimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Gagal menyimpan data obat", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(getContext(), "Tidak ada data obat untuk disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateButtonsVisibility() {
        int totalQty = 0;
        for (JenisObat obat : obatList) {
            totalQty += obat.getQty();
        }

        boolean isVisible = totalQty > 0;
        buttonPembayaran.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        buttonLanjutPilihObat.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
