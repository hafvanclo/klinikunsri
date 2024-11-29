package com.example.unsrihealthzone.ui.obat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unsrihealthzone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PembayaranNonBpjsFragment extends Fragment {
    private RadioGroup radioGroupPembayaran;
    private RadioButton rbDana, rbGopay, rbTunai, rbNonTunai;
    private TextView tvDetailPesanan, tvOngkosKirim, tvTotalHarga, tvLokasi;
    private Button btnBayar;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembayaran_nonbpjs_bottom_sheet, container, false);

        // Inisialisasi
        radioGroupPembayaran = view.findViewById(R.id.radioGroupPembayaran);
        rbDana = view.findViewById(R.id.rbDana);
        rbGopay = view.findViewById(R.id.rbGopay);
        rbTunai = view.findViewById(R.id.rbTunai);
        rbNonTunai = view.findViewById(R.id.rbNonTunai);
        tvDetailPesanan = view.findViewById(R.id.tvDetailPesanan);
        tvOngkosKirim = view.findViewById(R.id.tvOngkosKirim);
        tvTotalHarga = view.findViewById(R.id.tvTotalHarga);
        tvLokasi = view.findViewById(R.id.tvLokasi);
        btnBayar = view.findViewById(R.id.btnBayar);

        databaseReference = FirebaseDatabase.getInstance().getReference("PembayaranNonBpjs");

        // Event Listener
        radioGroupPembayaran.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isNonTunai = checkedId == R.id.rbNonTunai;
            view.findViewById(R.id.layoutNonTunai).setVisibility(isNonTunai ? View.VISIBLE : View.GONE);
        });

        btnBayar.setOnClickListener(v -> handlePembayaran());
        return view;
    }

    private void handlePembayaran() {
        String metodePembayaran = rbTunai.isChecked() ? "Tunai" : rbNonTunai.isChecked() ?
                (rbDana.isChecked() ? "DANA" : "GoPay") : "Tidak dipilih";

        if (metodePembayaran.equals("Tidak dipilih")) {
            Toast.makeText(requireContext(), "Pilih metode pembayaran terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> pembayaranData = new HashMap<>();
        pembayaranData.put("detailPesanan", tvDetailPesanan.getText().toString());
        pembayaranData.put("ongkosKirim", tvOngkosKirim.getText().toString());
        pembayaranData.put("total", tvTotalHarga.getText().toString());
        pembayaranData.put("lokasi", tvLokasi.getText().toString());
        pembayaranData.put("metodePembayaran", metodePembayaran);

        databaseReference.push().setValue(pembayaranData)
                .addOnSuccessListener(unused -> Toast.makeText(requireContext(), "Pembayaran berhasil disimpan!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(requireContext(), "Gagal menyimpan data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
