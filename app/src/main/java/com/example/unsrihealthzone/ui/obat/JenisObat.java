package com.example.unsrihealthzone.ui.obat;

public class JenisObat {

    private String nama;
    private String deskripsi;
    private int harga;
    private int qty;

    public JenisObat(String nama, String deskripsi, int harga, int qty) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.qty = qty;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
