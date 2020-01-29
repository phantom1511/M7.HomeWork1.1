package com.dastan.m7homework11;

import android.app.Application;

import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.data.remote.QuizApiClient;

import java.io.Serializable;

public class QuizApp extends Application {
    public static IQuizApiClient quizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        quizApiClient = new QuizApiClient();
    }
}
