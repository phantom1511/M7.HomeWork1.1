package com.dastan.m7homework11.data.remote;

import com.dastan.m7homework11.core.ICoreCallback;
import com.dastan.m7homework11.data.model.Categoty;
import com.dastan.m7homework11.data.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount, Integer category, String difficulty, QuestionsCallback callback);
    void getCategory(CategoryCallback categoryCallback);
    void getCount(Integer category, CountCallback countCallback);
    void getGlobal(GlobalCallback globalCallback);

    interface QuestionsCallback extends ICoreCallback<List<Question>>{}
    interface CategoryCallback extends ICoreCallback<List<Categoty>>{}
    interface CountCallback extends ICoreCallback<QuizCountResponse>{}
    interface GlobalCallback extends ICoreCallback<QuizGlobalResponse>{}
}
