package com.example.unsrihealthzone.ui.dokter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctorList;
    private OnDoctorClickListener listener;

    // Constructor
    public DoctorAdapter(List<Doctor> doctorList, OnDoctorClickListener listener) {
        this.doctorList = doctorList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.doctorName.setText(doctor.getName());
        holder.doctorSpecialty.setText(doctor.getSpecialty());
        holder.doctorDate.setText(doctor.getDate());

        holder.doctorImage.setImageResource(doctor.getImageResId());

        // Set onClickListener untuk tombol detail
        holder.detailButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDoctorClick(doctor); // Memanggil metode pada listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    // ViewHolder
    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        ImageView doctorImage;
        TextView doctorName, doctorSpecialty, doctorDate;
        Button detailButton;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorImage = itemView.findViewById(R.id.doctorImage);
            doctorName = itemView.findViewById(R.id.doctorName);
            doctorSpecialty = itemView.findViewById(R.id.doctorSpecialty);
            doctorDate = itemView.findViewById(R.id.doctorDate);

            detailButton = itemView.findViewById(R.id.detailButton);
        }
    }

    // Interface untuk menangani klik pada dokter
    public interface OnDoctorClickListener {
        void onDoctorClick(Doctor doctor);
    }
}
