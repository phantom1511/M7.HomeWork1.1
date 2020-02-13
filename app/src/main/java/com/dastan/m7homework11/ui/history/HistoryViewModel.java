package com.dastan.m7homework11.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.data.model.History;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    LiveData<List<History>> historyLiveData = QuizApp.historyStorage.getAllHistory();
}
