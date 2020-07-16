package com.example.bills2.ui.bills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BillsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bills");
    }

    public LiveData<String> getText() {
        return mText;
    }
}