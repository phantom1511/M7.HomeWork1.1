package com.dastan.m7homework11.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.data.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();
    public Integer count;

    public void fetchQuestions(int amount, Integer category, String difficulty){
        QuizApp.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                QuizViewModel.this.questions.postValue(questions);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    public QuizViewModel() {
        position.setValue(1);
        count = 1;
    }

    public void nextPage(){
        position.setValue(++count);
    }

    public void backPage(){
        position.setValue(--count);
    }
}
