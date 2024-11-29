package com.example.unsrihealthzone.ui.obat;


public class Obat {
    private String namaObat;
    private boolean isSelected;

    public Obat(String namaObat) {
        this.namaObat = namaObat;
        this.isSelected = false; // Default tidak terpilih
    }

    public String getNamaObat() {
        return namaObat;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
