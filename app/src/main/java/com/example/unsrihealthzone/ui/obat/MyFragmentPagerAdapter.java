package com.example.unsrihealthzone.ui.obat;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListRiwayat();
            case 1:
                return new ListSelesai();
            case 2:
                return new ListRiwayat();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Jumlah tab
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Dikirim";
            case 1:
                return "Selesai";
            case 2:
                return "Dibatalkan";
            default:
                return null;
        }
    }
}
