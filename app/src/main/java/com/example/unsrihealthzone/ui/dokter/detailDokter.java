package com.example.unsrihealthzone.ui.dokter;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.unsrihealthzone.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailDokter#newInstance} factory method to
 * create an instance of this fragment.
 */

public class detailDokter extends Fragment {
    private static final String ARG_NAME = "doctor_name";
    private static final String ARG_SPECIALTY = "doctor_specialty";
    private static final String ARG_IMAGE_RES = "doctor_image_res";
    private static final String ARG_ABOUT = "doctor_about";
    private static final String ARG_EXPERIENCE = "doctor_experience";

    private String doctorName;
    private String doctorSpecialty;
    private int doctorImageRes;
    private String aboutDoctor;
    private String doctorExperience;
    private Fragment calendarChooseFragment;

    private TextView day1, day2, day3, day4, day5, day6;
    private TextView selectedDay;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.getDefault());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_dokter, container, false);
        calendarChooseFragment = new CalendarChoose();
        day1 = view.findViewById(R.id.day1);
        day2 = view.findViewById(R.id.day2);
        day3 = view.findViewById(R.id.day3);
        day4 = view.findViewById(R.id.day4);
        day5 = view.findViewById(R.id.day5);
        day6 = view.findViewById(R.id.day6);

        // Set hari ini sebagai hari yang dipilih secara default
        setTodayAsSelected();

        // Atur listener untuk mengubah hari yang dipilih
        day1.setOnClickListener(v -> setSelectedDay(day1));
        day2.setOnClickListener(v -> setSelectedDay(day2));
        day3.setOnClickListener(v -> setSelectedDay(day3));
        day4.setOnClickListener(v -> setSelectedDay(day4));
        day5.setOnClickListener(v -> setSelectedDay(day5));
        day6.setOnClickListener(v -> setSelectedDay(day6));

        Button pilihDokterButton = view.findViewById(R.id.pilihdokter);
        pilihDokterButton.setOnClickListener(v -> openPilihTanggalFragment());
        return view;
    }

    private void openPilihTanggalFragment() {
        Log.d("DetailDokter", "Navigating to CalendarChooseFragment");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Pastikan ini adalah instance dari fragment yang ingin Anda ganti
        CalendarChoose calendarChooseFragment = new CalendarChoose();

        fragmentTransaction.replace(R.id.fragment_container, calendarChooseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    public static detailDokter newInstance(String name, String specialty, int imageRes, String about, String experience) {
        detailDokter fragment = new detailDokter();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_SPECIALTY, specialty);
        args.putInt(ARG_IMAGE_RES, imageRes);
        args.putString(ARG_ABOUT, about);
        args.putString(ARG_EXPERIENCE, experience);
        fragment.setArguments(args);
        return fragment;
    }

    private void setTodayAsSelected() {
        Calendar calendar = Calendar.getInstance();

        // Set teks untuk setiap hari dengan tanggal yang sesuai
        day1.setText(getDayLabelWithDate("Sen \n", calendar));
        day2.setText(getDayLabelWithDate("Sel\n", addDays(calendar, 1)));
        day3.setText(getDayLabelWithDate("Rab \n", addDays(calendar, 2)));
        day4.setText(getDayLabelWithDate("Kam \n", addDays(calendar, 3)));
        day5.setText(getDayLabelWithDate("Jum \n", addDays(calendar, 4)));
        day6.setText(getDayLabelWithDate("Sab \n", addDays(calendar, 5)));

        // Set hari ini sebagai hari yang dipilih
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                setSelectedDay(day1);
                day1.setText("Hari Ini\n" + day1.getText());
                break;
            case Calendar.TUESDAY:
                setSelectedDay(day2);
                day2.setText("Hari Ini\n" + day2.getText());
                break;
            case Calendar.WEDNESDAY:
                setSelectedDay(day3);
                day3.setText("Hari Ini\n" + day3.getText());
                break;
            case Calendar.THURSDAY:
                setSelectedDay(day4);
                day4.setText("Hari Ini\n" + day4.getText());
                break;
            case Calendar.FRIDAY:
                setSelectedDay(day5);
                day5.setText("Hari Ini\n" + day5.getText());
                break;
            case Calendar.SATURDAY:
                setSelectedDay(day6);
                day6.setText("Hari Ini\n" + day6.getText());
                break;
        }
    }

    // Fungsi untuk mendapatkan label hari dengan tanggal
    private String getDayLabelWithDate(String dayLabel, Calendar calendar) {
        return dayLabel + " " + dateFormat.format(calendar.getTime());
    }

    // Fungsi untuk menambahkan hari ke calendar
    private Calendar addDays(Calendar calendar, int days) {
        Calendar newCalendar = (Calendar) calendar.clone();
        newCalendar.add(Calendar.DAY_OF_MONTH, days);
        return newCalendar;
    }

    private void setSelectedDay(TextView day) {
        if (selectedDay != null) {
            selectedDay.setBackgroundResource(R.drawable.day_background_default);
            selectedDay.setTextColor(getResources().getColor(R.color.black));
        }
        selectedDay = day;
        selectedDay.setBackgroundResource(R.drawable.day_background_selected);
        selectedDay.setTextColor(Color.BLUE);
    }
}