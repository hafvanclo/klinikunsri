package com.example.unsrihealthzone.ui.obat;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unsrihealthzone.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiwayatObat#newInstance} factory method to
 * create an instance of this fragment.
 */


public class RiwayatObat extends Fragment {

    public RiwayatObat() {
        // Required empty public constructor
    }

    public static RiwayatObat newInstance(String param1, String param2) {
        RiwayatObat fragment = new RiwayatObat();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat_obat, container, false);

        // Inisialisasi TabLayout dan ViewPager
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);

        // Setup ViewPager dan Adapter
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        // Mengatur kustomisasi tab
        String[] tabTitles = new String[]{"Dikirim", "Selesai", "Dibatalkan"}; // Daftar nama tab
        tabLayout.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_tab); // Layout untuk tab kustom
                TextView tabText = tab.getCustomView().findViewById(R.id.tabText);

                tabText.setText(tabTitles[i]); // Set nama tab
            }
        }
        tabLayout.getTabAt(0).select();

        // Tambahkan listener untuk tab yang dipilih
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    TextView tabText = view.findViewById(R.id.tabText);
                    tabText.setBackgroundResource(R.drawable.tab_selected); // Ganti background untuk tab yang dipilih
                    tabText.setTextColor(Color.WHITE); // Ganti warna teks saat tab dipilih
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    TextView tabText = view.findViewById(R.id.tabText);
                    tabText.setBackgroundResource(R.drawable.tab_unselected); // Ganti background untuk tab yang tidak dipilih
                    tabText.setTextColor(Color.BLACK); // Kembalikan warna teks saat tab tidak dipilih
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Tidak perlu melakukan apa-apa di sini
            }


        });


        View firstTabView = tabLayout.getTabAt(0).getCustomView();
        if (firstTabView != null) {
            TextView firstTabText = firstTabView.findViewById(R.id.tabText);
            firstTabText.setBackgroundResource(R.drawable.tab_selected); // Set background untuk tab pertama
            firstTabText.setTextColor(Color.WHITE); // Set warna teks tab pertama
        }

        // Set tampilan untuk tab kedua (Selesai)
        View secondTabView = tabLayout.getTabAt(1).getCustomView();
        if (secondTabView != null) {
            TextView secondTabText = secondTabView.findViewById(R.id.tabText);
            secondTabText.setBackgroundResource(R.drawable.tab_unselected); // Ganti background untuk tab kedua
            secondTabText.setTextColor(Color.BLACK); // Set warna teks untuk tab kedua
        }

        // Set tampilan untuk tab ketiga (Dibatalkan)
        View thirdTabView = tabLayout.getTabAt(2).getCustomView();
        if (thirdTabView != null) {
            TextView thirdTabText = thirdTabView.findViewById(R.id.tabText);
            thirdTabText.setBackgroundResource(R.drawable.tab_unselected); // Ganti background untuk tab ketiga
            thirdTabText.setTextColor(Color.BLACK); // Set warna teks untuk tab ketiga
        }


        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi kembali ke halaman sebelumnya
                requireActivity().onBackPressed();
            }
        });

        return view; // Kembalikan view di sini
    }


}
