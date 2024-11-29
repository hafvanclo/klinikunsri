package com.example.unsrihealthzone.ui.obat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unsrihealthzone.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BpjsInputNomor extends Fragment {

    private EditText editTextNama, editTextBpjs;
    private Button verifikasiButton;

    // Firebase Realtime Database reference
    private DatabaseReference mDatabase;

    public BpjsInputNomor() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bpjs_input_nomor, container, false);

        // Inisialisasi Firebase Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        // Inisialisasi View
        editTextNama = view.findViewById(R.id.editTextNama);
        editTextBpjs = view.findViewById(R.id.editTextBpjs);
        verifikasiButton = view.findViewById(R.id.verifikasiButton);
        ImageView backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi kembali ke halaman sebelumnya
                requireActivity().onBackPressed();
            }
        });

        // Tambahkan TextWatcher untuk memeriksa input dan mengubah warna button
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextNama.addTextChangedListener(textWatcher);
        editTextBpjs.addTextChangedListener(textWatcher);

        // Set onClickListener untuk verifikasiButton
        verifikasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyBpjs();
            }
        });

        return view;
    }

    private void checkInput() {
        // Jika kedua input tidak kosong, ubah warna button
        String nama = editTextNama.getText().toString().trim();
        String bpjs = editTextBpjs.getText().toString().trim();

        if (!nama.isEmpty() && !bpjs.isEmpty()) {
            verifikasiButton.setBackgroundResource(R.drawable.rounded_verif); // Tetapkan drawable border
            verifikasiButton.setTextColor(getResources().getColor(R.color.white)); // Ubah warna teks
            verifikasiButton.getBackground().setColorFilter(getResources().getColor(R.color.dark_blue), PorterDuff.Mode.SRC_ATOP); // Ubah warna latar belakang
        } else {
            verifikasiButton.setBackgroundResource(R.drawable.rounded_verif); // Tetapkan drawable border
            verifikasiButton.setTextColor(getResources().getColor(R.color.white)); // Ubah warna teks
            verifikasiButton.getBackground().setColorFilter(getResources().getColor(R.color.dark_blue), PorterDuff.Mode.SRC_ATOP); // Ubah warna latar belakang
        }
    }

    private void verifyBpjs() {
        String nama = editTextNama.getText().toString().trim();
        String bpjs = editTextBpjs.getText().toString().trim();

        // Jika nama adalah "Harvey Cantwell", tampilkan pop-up valid, jika tidak tampilkan pop-up tidak valid
        if (nama.equals("Harvey Cantwell")) {
            showAlertDialog(true);
            saveNameToSession(nama);
            saveBpjsToDatabase(nama, bpjs); // Simpan data BPJS ke Firebase

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment pilihObatFragment = new PilihObat();
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_pilih_obat);
                    navController.navigate(R.id.navigation_pilih_obat);
                }
            }, 2000);
        } else {
            showAlertDialog(false); // Nomor BPJS Tidak Valid
        }
    }

    private void saveNameToSession(String name) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name); // Simpan nama dengan key "userName"
        editor.apply(); // Commit perubahan
    }

    private void saveBpjsToDatabase(String nama, String bpjs) {
        // Menyimpan data pengguna BPJS ke Firebase
        String userId = mDatabase.push().getKey(); // Menghasilkan ID unik untuk pengguna baru
        if (userId != null) {
            UserBpjs user = new UserBpjs(nama, bpjs); // Buat objek UserBpjs
            mDatabase.child(userId).setValue(user); // Simpan data ke database Firebase
            Toast.makeText(getContext(), "Data BPJS disimpan!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog(boolean isValid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(isValid ? R.layout.dialog_valid : R.layout.dialog_invalid, null);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false); // Tidak bisa dibatalkan dengan menekan di luar dialog
        alertDialog.show();

        // Menutup dialog secara otomatis setelah beberapa detik
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            alertDialog.dismiss();
        }, 2000); // Menutup dialog setelah 3 detik
    }

    // Kelas untuk data pengguna BPJS
    public static class UserBpjs {
        public String nama;
        public String bpjs;

        public UserBpjs() {
            // Default constructor required for Firebase
        }

        public UserBpjs(String nama, String bpjs) {
            this.nama = nama;
            this.bpjs = bpjs;
        }
    }
}
