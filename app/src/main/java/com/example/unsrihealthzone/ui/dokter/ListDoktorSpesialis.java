package com.example.unsrihealthzone.ui.dokter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unsrihealthzone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDoktorSpesialis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDoktorSpesialis extends Fragment implements DoctorSpecialisAdapter.OnDoctorClickListener {
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_doktor_spesialis, container, false);
        recyclerView = view.findViewById(R.id.doctorListSpesialis);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Siapkan data dokter
        List<DoctorSpesialis> doctorList = new ArrayList<>();
        doctorList.add(new DoctorSpesialis("Dr. Adetya", "Spesialisasi Gigi", "Sabtu, 12 Juni",  R.drawable.ic_doctor_placeholder));
        doctorList.add(new DoctorSpesialis("Dr. Bessie Coleman", "Spesialisasi THT", "Selasa, 23 Juni", R.drawable.ic_doctor_placeholder));
        doctorList.add(new DoctorSpesialis("Dr. Babe Didrikson", "Spesialisasi Kulit", "Rabu, 30 Juni", R.drawable.ic_doctor_placeholder));

        // Set up adapter dengan listener
        DoctorSpecialisAdapter adapter = new DoctorSpecialisAdapter(doctorList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDoctorClick(DoctorSpesialis doctor) {
        // Lakukan aksi ketika dokter diklik, misalnya mengganti fragment atau menampilkan detail
    }
}