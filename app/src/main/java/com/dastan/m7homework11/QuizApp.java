package com.dastan.m7homework11;

import android.app.Application;

import com.dastan.m7homework11.data.QuizRepository;
import com.dastan.m7homework11.data.history.HistoryStorage;
import com.dastan.m7homework11.data.history.IHistoryStorage;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.data.remote.QuizApiClient;

public class QuizApp extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;


    @Override
    public void onCreate() {
        super.onCreate();
        QuizRepository quizRepository = new QuizRepository(new QuizApiClient(), new HistoryStorage());

        quizApiClient = quizRepository;
        historyStorage = quizRepository;
    }
}
