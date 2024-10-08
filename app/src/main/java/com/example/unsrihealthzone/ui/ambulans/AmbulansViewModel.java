package com.example.unsrihealthzone.ui.ambulans;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmbulansViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AmbulansViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}