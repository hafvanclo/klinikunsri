package com.example.unsrihealthzone.ui.call;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.databinding.FragmentCallBinding;
import com.example.unsrihealthzone.databinding.FragmentDokterBinding;
import com.example.unsrihealthzone.ui.dokter.DokterSpesialis;
import com.example.unsrihealthzone.ui.dokter.ListDoktorSpesialis;
import com.example.unsrihealthzone.ui.obat.BpjsInputNomor;

public class CallFragment extends Fragment {

    private CallViewModel mViewModel;

    public static CallFragment newInstance() {
        return new CallFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout
        View root = inflater.inflate(R.layout.fragment_call, container, false);
        ImageView backButton = root.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi kembali ke halaman sebelumnya
                requireActivity().onBackPressed();
            }
        });
        // Find the button by its ID
        FrameLayout bpjsButton = root.findViewById(R.id.bpjs);
        FrameLayout umumButton = root.findViewById(R.id.umum);
        if (bpjsButton == null) {
            Log.e("CallFragment", "BPJS Button is null!");
        } else {
            bpjsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CallFragment", "BPJS clicked!");


                    // Navigasi menggunakan NavController
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_calling);
                    navController.navigate(R.id.navigation_bpjs);
                }
            });
            umumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CallFragment", "BPJS clicked!");

                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("userName"); // Menghapus userName
                    editor.apply();
                    // Navigasi menggunakan NavController
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_calling);
                    navController.navigate(R.id.navigation_pilih_obat);
                }
            });
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CallViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // No need to set binding to null since we are not using it
    }
}