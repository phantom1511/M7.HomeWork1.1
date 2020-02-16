package com.dastan.m7homework11.data;

import androidx.lifecycle.LiveData;

import com.dastan.m7homework11.data.history.IHistoryStorage;
import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.Question;
import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    private Question shuffleAnswers(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswer());

        Collections.shuffle(answers);
        question.setAnswers(answers);

        return question;
    }


    @Override
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        quizApiClient.getQuestions(amount, category, difficulty, new QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, shuffleAnswers(result.get(i)));
                }
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getCategory(CategoryCallback categoryCallback) {

    }

    @Override
    public void getCount(Integer category, CountCallback countCallback) {

    }

    @Override
    public void getGlobal(GlobalCallback globalCallback) {

    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return historyStorage.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return historyStorage.getAllHistory();
    }

    @Override
    public void delete(int id) {
        historyStorage.getQuizResult(id);
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return historyStorage.getQuizResult(id);
    }

    @Override
    public int saveQuizResult(QuizResult result) {
        return historyStorage.saveQuizResult(result);
    }
}
