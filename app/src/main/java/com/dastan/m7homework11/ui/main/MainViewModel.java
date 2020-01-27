package com.dastan.m7homework11.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>();
    public int count = 0;

    public MainViewModel() {

    }

    public void addOne(){
        count++;
        counter.setValue(count);
    }

    public void minusOne(){
        count--;
        counter.setValue(count);
    }
}
