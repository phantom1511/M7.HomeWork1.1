package com.dastan.m7homework11.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.QuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    private List<QuizResult> mHistory;

    LiveData<List<History>> historyLiveData = QuizApp.historyStorage.getAllHistory();
    LiveData<List<QuizResult>> quizResultLiveData = QuizApp.historyStorage.getAll();
}
