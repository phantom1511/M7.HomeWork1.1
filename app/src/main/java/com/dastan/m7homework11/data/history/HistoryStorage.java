package com.dastan.m7homework11.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private  HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao) {
        dao = historyDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult result) {
        return (int) dao.insert(result);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResult -> {
            ArrayList<History> histories = new ArrayList<>();
            if (quizResult.size() > 0){
                for (int i = 0; i < quizResult.size(); i++) {
                    histories.add(i, new History(quizResult.get(i).getId(),
                            quizResult.get(i).getCategory(),
                            quizResult.get(i).getDifficulty(),
                            quizResult.get(i).getCorrectAnswersAmount(),
                            quizResult.get(i).getQuestions().size(),
                            quizResult.get(i).getCreatedAt()));
                }
            }
            return histories;
        });
    }

    @Override
    public void delete(QuizResult result) {
        dao.delete(result);
    }

    @Override
    public void deleteAll() {

    }
}
