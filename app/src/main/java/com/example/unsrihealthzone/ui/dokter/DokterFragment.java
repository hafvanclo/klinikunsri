package com.example.unsrihealthzone.ui.dokter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.databinding.FragmentDokterBinding;

import java.util.ArrayList;
import java.util.List;
public class DokterFragment extends Fragment implements DoctorAdapter.OnDoctorClickListener {

    private FragmentDokterBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DokterViewModel dokterViewModel = new ViewModelProvider(this).get(DokterViewModel.class);

        binding = FragmentDokterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.doctorList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Siapkan data dokter
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Dr. Adetya", "Spesialisasi Gigi", "Sabtu, 12 Juni", "11:00 - 12:00 WIB", R.drawable.ic_doctor_placeholder));
        doctorList.add(new Doctor("Dr. Bessie Coleman", "Spesialisasi THT", "Selasa, 23 Juni", "11:00 - 12:00 WIB", R.drawable.ic_doctor_placeholder));
        doctorList.add(new Doctor("Dr. Babe Didrikson", "Spesialisasi Kulit", "Rabu, 30 Juni", "11:00 - 12:00 WIB", R.drawable.ic_doctor_placeholder));
        // Tambahkan dokter lainnya jika diperlukan...

        // Set up adapter
        DoctorAdapter adapter = new DoctorAdapter(doctorList, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override

    public void onDoctorClick(Doctor doctor) {
        // Buat instance fragment DetailDokterFragment dengan data dokter
        detailDokter detailDokterFragment = detailDokter.newInstance(
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getImageResId(),
                "Tentang dokter...",
                "8 Tahun"
        );

        // Ambil FragmentManager dari Activity
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Ganti fragment_container di Activity dengan DetailDokterFragment
        fragmentTransaction.replace(R.id.fragment_container, detailDokterFragment);
        fragmentTransaction.addToBackStack(null); // Untuk bisa kembali ke fragment sebelumnya
        fragmentTransaction.commit();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Menghindari kebocoran memori
    }
}
