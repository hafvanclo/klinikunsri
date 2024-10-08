package com.example.unsrihealthzone.ui.ambulans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unsrihealthzone.MainActivity;
import com.example.unsrihealthzone.R;

public class CallingAmbulance extends AppCompatActivity {


    private TextView textView;
    private ImageView callEnded;
    private int seconds = 0; // Variabel untuk menghitung detik
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_ambulance); // Ganti dengan layout Anda

        textView = findViewById(R.id.textView12);
        callEnded = findViewById(R.id.call_ended);

        // Memulai timer
        startTimer();

        // Set listener untuk ImageView
        callEnded.setOnClickListener(v -> {
            // Kembali ke MainActivity
            Intent intent = new Intent(CallingAmbulance.this, MainActivity.class);
            startActivity(intent);
            finish(); // Tutup activity ini jika ingin
        });
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seconds++; // Tambah detik
                updateTimerText(); // Perbarui tampilan timer
                handler.postDelayed(this, 1000); // Jalankan lagi setelah 1 detik
            }
        }, 1000);
    }

    private void updateTimerText() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        String time = String.format("%02d : %02d", minutes, secs);
        textView.setText(time); // Set teks untuk TextView
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Menghapus semua callback untuk menghindari memory leak
    }
}