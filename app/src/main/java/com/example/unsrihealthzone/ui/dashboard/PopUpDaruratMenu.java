package com.example.unsrihealthzone.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.unsrihealthzone.R;
import com.example.unsrihealthzone.ui.ambulans.PilihLokasi;
import com.example.unsrihealthzone.ui.ambulans.SendLokasi;

public class PopUpDaruratMenu extends DialogFragment {
    public PopUpDaruratMenu() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pop_up_darurat_menu, container, false);


        ImageView iconCancel = view.findViewById(R.id.icon_cancel_menu);
        Button buttonTidak = view.findViewById(R.id.button_tidak_menu);
        Button buttonYa = view.findViewById(R.id.button_ya_menu);

        iconCancel.setOnClickListener(v -> {
            dismiss();
        });

        buttonTidak.setOnClickListener(v -> {
            dismiss();
        });

        buttonYa.setOnClickListener(v -> {
            dismiss();

            Intent intent = new Intent(getActivity(), SendLokasi.class);
            startActivity(intent);
        });




        // Return the inflated view
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set dialog size and position
        if (getDialog() != null && getDialog().getWindow() != null) {
            // Set the width and height of the dialog
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // Optionally set other layout attributes, such as gravity
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.gravity = Gravity.CENTER;  // Set the dialog in the center
            getDialog().getWindow().setAttributes(params);
        }
    }
}