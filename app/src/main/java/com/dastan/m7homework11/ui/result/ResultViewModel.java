package com.dastan.m7homework11.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.data.model.QuizResult;

public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResult.setValue(QuizApp.quizDatabase.historyDao().get(id));
    }
}
