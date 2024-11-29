package com.example.unsrihealthzone.ui.obat;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.R;

import java.util.List;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ObatViewHolder> {

    private List<Obat> obatList;
    private OnObatClickListener listener;

    public interface OnObatClickListener {
        void onObatClick(int position);
    }

    public ObatAdapter(List<Obat> obatList, OnObatClickListener listener) {
        this.obatList = obatList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ObatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.obat_item, parent, false);
        return new ObatViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ObatViewHolder holder, int position) {
        Obat obat = obatList.get(position);
        holder.btnObat.setText(obat.getNamaObat());
        holder.btnObat.setSelected(obat.isSelected());
        if (obat.isSelected()) {
            holder.btnObat.setTextColor(Color.WHITE); // Jika terpilih, teks jadi putih
        }
    }

    @Override
    public int getItemCount() {
        return obatList.size();
    }

    public static class ObatViewHolder extends RecyclerView.ViewHolder {
        Button btnObat;

        public ObatViewHolder(@NonNull View itemView, final OnObatClickListener listener) {
            super(itemView);
            btnObat = itemView.findViewById(R.id.btn_obat);

            btnObat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onObatClick(position);
                        }
                    }
                }
            });
        }
    }
}
