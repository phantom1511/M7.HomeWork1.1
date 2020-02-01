package com.dastan.m7homework11.data.remote;

import android.icu.util.ULocale;

import com.dastan.m7homework11.core.ICoreCallback;
import com.dastan.m7homework11.model.Categoty;
import com.dastan.m7homework11.model.Global;
import com.dastan.m7homework11.model.Question;
import com.dastan.m7homework11.model.QuestionCount;

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
