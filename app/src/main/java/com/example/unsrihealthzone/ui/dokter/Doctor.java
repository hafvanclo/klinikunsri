package com.example.unsrihealthzone.ui.dokter;

public class Doctor {
    private String name;
    private String specialty;
    private String date;
    private String time;
    private int imageResId;

    public Doctor(String name, String specialty, String date, String time, int imageResId) {
        this.name = name;
        this.specialty = specialty;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public int getImageResId() {
        return imageResId;
    }
}

