package com.example.unsrihealthzone.ui.obat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.unsrihealthzone.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PembayaranFragment extends BottomSheetDialogFragment {
    private Spinner spinnerLokasi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembayaran_bottom_sheet, container, false);


        Button pesanButton = view.findViewById(R.id.pesanButton);
        // Menggunakan view.findViewById untuk mendapatkan referensi spinner
        spinnerLokasi = view.findViewById(R.id.spinnerLokasi);

        // Menyediakan data untuk spinner
        String[] lokasi = {
                "Lokasi Sekarang",
                "Lokasi Lain 1",
                "Lokasi Lain 2"
        };

        // Membuat adapter dan mengatur spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, lokasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLokasi.setAdapter(adapter);

        pesanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hapus data userName dari SharedPreferences
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("userName"); // Menghapus userName
                editor.apply(); // Simpan perubahan

                SuccessDialog dialog = new SuccessDialog(requireContext());
                dialog.show();

                // Menghilangkan dialog setelah 2 detik
                new Handler().postDelayed(dialog::dismiss, 2000);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_riwayat); // Ensure the ID is correct
                navController.navigate(R.id.navigation_riwayat);
            }
        });


        return view;
    }


}
