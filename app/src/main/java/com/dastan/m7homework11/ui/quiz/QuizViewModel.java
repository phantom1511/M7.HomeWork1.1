package com.dastan.m7homework11.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>();
    public int count = 0;

    public QuizViewModel() {

    }
}
