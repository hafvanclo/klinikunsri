package com.example.unsrihealthzone.ui.ambulans;

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


public class popup_darurat extends DialogFragment {

    public popup_darurat() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popup_darurat, container, false);


        ImageView iconCancel = view.findViewById(R.id.icon_cancel);
        Button buttonTidak = view.findViewById(R.id.button_tidak);
        Button buttonYa = view.findViewById(R.id.button_ya);

        iconCancel.setOnClickListener(v -> {
            dismiss();
        });

        buttonTidak.setOnClickListener(v -> {
            dismiss();
        });

        buttonYa.setOnClickListener(v -> {
            dismiss();

            Intent intent = new Intent(getActivity(), PilihLokasi.class);
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
