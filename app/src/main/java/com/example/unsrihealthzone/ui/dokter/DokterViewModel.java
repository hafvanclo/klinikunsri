package com.example.unsrihealthzone.ui.dokter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DokterViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DokterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}