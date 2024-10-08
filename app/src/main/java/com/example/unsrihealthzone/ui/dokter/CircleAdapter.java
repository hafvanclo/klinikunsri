package com.example.unsrihealthzone.ui.dokter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.R;

import java.util.List;
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CircleViewHolder> {
    private List<Integer> icons;
    private List<String> poliNames; // Tambahkan daftar nama poli
    private int selectedItem = -1; // Untuk melacak item yang dipilih

    public CircleAdapter(List<Integer> icons, List<String> poliNames) {
        this.icons = icons;
        this.poliNames = poliNames;
    }

    @NonNull
    @Override
    public CircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle, parent, false);
        return new CircleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleViewHolder holder, int position) {
        holder.iconView.setImageResource(icons.get(position));
        holder.poliName.setText(poliNames.get(position)); // Set nama poli

        // Set warna lingkaran berdasarkan item yang dipilih

        if (selectedItem == position) {
            holder.circleView.setBackgroundResource(R.drawable.circle_active);
            holder.iconView.setColorFilter(android.graphics.Color.WHITE); // Ubah ikon menjadi putih
        } else {
            holder.circleView.setBackgroundResource(R.drawable.circle_inactive);
            holder.iconView.setColorFilter(android.graphics.Color.parseColor("#FF4894FE")); // Ubah ikon menjadi dark blue
        }

        // Set listener untuk menangani pemilihan item
        holder.itemView.setOnClickListener(v -> {
            selectedItem = position;
            notifyDataSetChanged(); // Notifikasi perubahan untuk mengupdate tampilan
        });
    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    static class CircleViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        View circleView; // Menggunakan View untuk lingkaran
        TextView poliName; // Tambahkan TextView untuk nama poli

        CircleViewHolder(View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.iconView);
            circleView = itemView.findViewById(R.id.circleView);
            poliName = itemView.findViewById(R.id.poliName); // Inisialisasi TextView
        }
    }
}