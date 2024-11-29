package com.example.unsrihealthzone.ui.ambulans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.example.unsrihealthzone.R;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopUpLokasiSended#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopUpLokasiSended extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false); // Agar popup tidak bisa dibatalkan dengan menekan di luar
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout untuk pop-up
        View view = inflater.inflate(R.layout.fragment_pop_up_lokasi_sended, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        new Handler().postDelayed(() -> {
            dismiss();

            startActivity(new Intent(getActivity(), CallingAmbulance.class));
        }, 3000);
    }
}
