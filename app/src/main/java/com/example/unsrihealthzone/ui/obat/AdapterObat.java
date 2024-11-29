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


public class AdapterObat extends RecyclerView.Adapter<AdapterObat.ViewHolder> {
    private List<JenisObat> obatList;
    private Context context;
    private OnObatClickListener listener;

    public AdapterObat(Context context, List<JenisObat> obatList, OnObatClickListener listener) {
        this.context = context;
        this.obatList = obatList;
        this.listener = listener;
    }

    public interface OnObatClickListener {
        void onQtyChange(int position, int qty);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_obat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JenisObat obat = obatList.get(position);
        holder.namaObat.setText(obat.getNama());
        holder.hargaObat.setText("Rp " + obat.getHarga());
        holder.deskripsiObat.setText(obat.getDeskripsi());
        holder.qtyObat.setText(String.valueOf(obat.getQty()));

        holder.buttonPlus.setOnClickListener(v -> {
            obat.setQty(obat.getQty() + 1);
            listener.onQtyChange(position, obat.getQty());
            notifyItemChanged(position);
            changeButtonColor(holder.buttonPlus);

        });

        holder.buttonMinus.setOnClickListener(v -> {
            if (obat.getQty() > 0) {
                obat.setQty(obat.getQty() - 1);
                listener.onQtyChange(position, obat.getQty());
                notifyItemChanged(position);
                changeButtonColor(holder.buttonMinus);
            }
        });

        holder.qtyObat.setText(String.valueOf(obat.getQty()));
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
        TextView namaObat, hargaObat, deskripsiObat, qtyObat;
        Button buttonPlus, buttonMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageObat = itemView.findViewById(R.id.image_obat);
            namaObat = itemView.findViewById(R.id.nama_obat);
            hargaObat = itemView.findViewById(R.id.harga_obat);
            deskripsiObat = itemView.findViewById(R.id.deskripsi_obat);
            qtyObat = itemView.findViewById(R.id.qty_obat);
            buttonPlus = itemView.findViewById(R.id.button_plus);
            buttonMinus = itemView.findViewById(R.id.button_minus);
        }
    }



}