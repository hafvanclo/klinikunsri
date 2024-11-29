package com.example.unsrihealthzone.ui.obat;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unsrihealthzone.R;

import java.util.List;

public class AdapterSelesai extends RecyclerView.Adapter<AdapterRiwayat.ViewHolder> {
    private List<JenisObat> obatList;
    private Context context;
    private AdapterRiwayat.OnObatClickListener listener;

    public AdapterSelesai(Context context, List<JenisObat> obatList, AdapterRiwayat.OnObatClickListener listener) {
        this.context = context;
        this.obatList = obatList;
        this.listener = listener;
    }

    public interface OnObatClickListener {
        void onQtyChange(int position, int qty);
    }

    @NonNull
    @Override
    public AdapterRiwayat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_selesai, parent, false);
        return new AdapterRiwayat.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRiwayat.ViewHolder holder, int position) {
        JenisObat obat = obatList.get(position);
        holder.namaObat.setText(obat.getNama());
        holder.hargaObat.setText("Rp " + obat.getHarga());
        holder.deskripsiObat.setText(obat.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return obatList.size();
    }
    private void changeButtonColor(Button button) {
        // Change the background color when the button is clicked
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue)); // Use your desired color
        // Reset the color after a short delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setBackgroundColor(ContextCompat.getColor(context, R.color.white)); // Use the default button color
        }, 200); // Change the delay time as needed
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageObat;
        TextView namaObat, hargaObat, deskripsiObat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageObat = itemView.findViewById(R.id.image_obat);
            namaObat = itemView.findViewById(R.id.nama_obat);
            hargaObat = itemView.findViewById(R.id.harga_obat);
            deskripsiObat = itemView.findViewById(R.id.deskripsi_obat);

        }
    }



}
