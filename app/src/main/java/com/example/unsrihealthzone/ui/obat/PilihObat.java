package com.example.unsrihealthzone.ui.obat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unsrihealthzone.R;

import java.util.ArrayList;
import java.util.List;
public class PilihObat extends Fragment {

    private RecyclerView recyclerView;
    private ObatAdapter adapter;
    private List<Obat> obatList;
    private TextView countChoose; // Tambahkan variabel untuk count_choose
    private int selectedCount = 0;
    ImageView addObat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pilih_obat, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewObat);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Inisialisasi count_choose TextView
        countChoose = view.findViewById(R.id.count_choose);

        // Inisialisasi data obat
        obatList = new ArrayList<>();
        obatList.add(new Obat("Salep"));
        obatList.add(new Obat("Vitamin"));
        obatList.add(new Obat("Suplemen"));
        obatList.add(new Obat("Obat \nAntipiretik"));
        obatList.add(new Obat("Obat \nAntiinflamasi"));
        obatList.add(new Obat("Obat \nAntihistamin"));
        obatList.add(new Obat("Obat \nPencernaan"));
        obatList.add(new Obat("Susu Ibu \nHamil"));
        obatList.add(new Obat("Obat Mata \ndan Telinga"));
        obatList.add(new Obat("Obat Kulit"));

        adapter = new ObatAdapter(obatList, new ObatAdapter.OnObatClickListener() {
            @Override
            public void onObatClick(int position) {
                // Logika ketika obat dipilih
                Obat obat = obatList.get(position);
                obat.setSelected(!obat.isSelected());

                // Update jumlah pilihan
                if (obat.isSelected()) {
                    selectedCount++;
                } else {
                    selectedCount--;
                }

                // Perbarui TextView
                countChoose.setText(String.valueOf(selectedCount));
                adapter.notifyItemChanged(position);
            }
        });



        recyclerView.setAdapter(adapter);
        addObat = view.findViewById(R.id.add_obat); // Ensure this ID matches your XML

        addObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CallFragment", "BPJS clicked!");
                Fragment pilihObatFragment = new AddObat();

                // Navigasi menggunakan NavController
                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_add_obat); // Ensure the ID is correct
                navController.navigate(R.id.navigation_add_obat);
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

        return view;
    }
}
