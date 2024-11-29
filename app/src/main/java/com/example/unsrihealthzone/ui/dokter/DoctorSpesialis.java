package com.example.unsrihealthzone.ui.dokter;

public class DoctorSpesialis {
    private String name;
    private String specialty;
    private String date;

    private int imageResId;

    public DoctorSpesialis(String name, String specialty, String date, int imageResId) {
        this.name = name;
        this.specialty = specialty;
        this.date = date;

        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDate() {
        return date;
    }



    public int getImageResId() {
        return imageResId;
    }
}

