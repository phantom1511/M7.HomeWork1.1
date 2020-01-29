package com.dastan.m7homework11.data.remote;

import com.dastan.m7homework11.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(QuestionsCallback callback);

    public interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(Exception e);
    }
}
