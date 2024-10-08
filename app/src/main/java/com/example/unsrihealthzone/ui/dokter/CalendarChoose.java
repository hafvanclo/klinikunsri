package com.example.unsrihealthzone.ui.dokter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unsrihealthzone.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarChoose#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarChoose extends Fragment {

    private CalendarView calendarView;
    private Button pilihTanggal;
    private long selectedDate = -1; // Set ke -1 untuk menandakan tidak ada tanggal yang dipilih
    private TextView filterSpecialty;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_choose, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        pilihTanggal = view.findViewById(R.id.pilihtanggal);

        // Nonaktifkan tombol pada awal
        pilihTanggal.setEnabled(false);
        pilihTanggal.setBackgroundResource(R.drawable.rounded_button_background); // Ganti ke background yang tidak aktif

        // Set listener untuk CalendarView
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // Menghitung tanggal yang dipilih
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTimeInMillis();

            // Enable tombol setelah tanggal dipilih
            pilihTanggal.setEnabled(true);
            pilihTanggal.setBackgroundResource(R.drawable.rounded_button_background_selected); // Ganti latar belakang sesuai kebutuhan

            // Ganti warna teks tombol menjadi putih ketika tanggal dipilih
            pilihTanggal.setTextColor(getResources().getColor(R.color.white)); // Menggunakan warna putih
        });

        // Set listener untuk tombol Pilih Tanggal
        pilihTanggal.setOnClickListener(v -> {
            if (selectedDate != -1) { // Cek jika tanggal valid dipilih
                // Lakukan tindakan setelah tanggal dipilih
                Toast.makeText(getContext(), "Tanggal yang dipilih: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
        filterSpecialty = view.findViewById(R.id.filterSpecialty);

        filterSpecialty.setOnClickListener(v -> {
            // Ganti fragment di FrameLayout
            replaceFragment(new DokterSpesialis());
        });

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