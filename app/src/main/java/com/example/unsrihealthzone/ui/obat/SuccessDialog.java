package com.example.unsrihealthzone.ui.obat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.unsrihealthzone.R;
public class SuccessDialog extends Dialog {

    public SuccessDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_success);

        // Mengatur lebar dan tinggi dialog
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);

        // Menghilangkan padding atau margin
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Anda bisa menambahkan pengaturan lainnya di sini jika diperlukan
    }
}
