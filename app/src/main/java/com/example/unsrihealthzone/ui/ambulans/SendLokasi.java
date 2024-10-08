package com.example.unsrihealthzone.ui.ambulans;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unsrihealthzone.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class SendLokasi extends AppCompatActivity {


    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));
        setContentView(R.layout.activity_send_lokasi);

        // Inisialisasi MapView
        map = findViewById(R.id.map);
        map.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        // Mengatur posisi awal peta
        map.getController().setZoom(15.0);
        map.getController().setCenter(new org.osmdroid.util.GeoPoint(-6.200000, 106.816666)); // Ganti dengan koordinat yang diinginkan

        // Menambahkan marker pada peta
        Marker marker = new Marker(map);
        marker.setPosition(new org.osmdroid.util.GeoPoint(-6.200000, 106.816666)); // Ganti dengan koordinat yang diinginkan
        marker.setTitle("Marker Title"); // Judul marker
        map.getOverlays().add(marker);
        ImageView backButton = findViewById(R.id.backButton);

        // Atur listener untuk tombol kembali
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke halaman sebelumnya
                finish(); // Mengakhiri activity ini dan kembali ke activity sebelumnya
            }
        });

        Button btnSendLokasi = findViewById(R.id.btn_send_lokasi);
        btnSendLokasi.setOnClickListener(v -> {
            PopUpLokasiSended popUp = new PopUpLokasiSended();
            popUp.show(getSupportFragmentManager(), "PopUpLokasiSended");
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Memanggil metode ini untuk menampilkan peta dengan benar
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDetach();
    }



}