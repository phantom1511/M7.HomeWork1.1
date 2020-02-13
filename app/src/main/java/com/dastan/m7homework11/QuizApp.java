package com.dastan.m7homework11;

import android.app.Application;

import androidx.room.Room;

import com.dastan.m7homework11.data.QuizRepository;
import com.dastan.m7homework11.data.db.QuizDatabase;
import com.dastan.m7homework11.data.history.HistoryStorage;
import com.dastan.m7homework11.data.history.IHistoryStorage;
import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.data.remote.QuizApiClient;

public class QuizApp extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository quizRepository;
    public static QuizDatabase quizDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        quizDatabase = Room.databaseBuilder(this, QuizDatabase.class, "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();

        quizDatabase.historyDao();
        quizRepository = new QuizRepository(new QuizApiClient(), new HistoryStorage(quizDatabase.historyDao()));

        quizApiClient = quizRepository;
        historyStorage = quizRepository;
    }
}
