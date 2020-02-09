package com.dastan.m7homework11.data.history;

import androidx.lifecycle.LiveData;

import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {


    @Override
    public void save(QuizResult result) {

    }

    @Override
    public void delete(QuizResult result) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public QuizResult get(int id) {
        return null;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult result) {
        return 0;
    }
}
