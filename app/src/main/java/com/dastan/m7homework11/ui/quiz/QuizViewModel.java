package com.dastan.m7homework11.ui.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.core.SingleLiveEvent;
import com.dastan.m7homework11.data.model.Question;
import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;
    private List<Question> mQuestion;
    private int id;
    private String resultCategory, resultDifficulty;
    private Integer count;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentPosition = new MutableLiveData<>();

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();


    public QuizViewModel() {
        currentPosition.setValue(0);
        count = 0;
    }

    void fetchQuestions(int amount, Integer category, String difficulty) {
        QuizApp.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestion = result;
                questions.postValue(mQuestion);
                if (category != null){
                    resultCategory = mQuestion.get(0).getCategory();
                }else{
                    resultCategory = "Mixed";
                }
                if (difficulty != null){
                    resultDifficulty = mQuestion.get(0).getDifficulty().toString();
                }else{
                    resultDifficulty = "All";
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    private int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int i = 0; i <= mQuestion.size() - 1 ; i++) {
            if (mQuestion.get(i).getSelectedAnswerPosition() != null){
                String correctAnswer = mQuestion.get(i).getCorrectAnswer();
                String selectedAnswer = mQuestion.get(i).getAnswers()
                        .get(mQuestion.get(i).getSelectedAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }
        return correctAnswersAmount;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                id,
                resultCategory,
                resultDifficulty,
                mQuestion,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = QuizApp.historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    private String getCategory() {
        String category = "Mixed";
        if (mQuestion.get(0).getCategory().equals(mQuestion.get(1).getCategory())) {
            category = mQuestion.get(0).getCategory();
        }
        return category;
    }

    private String getDifficulty() {
        String category = "All";
        if (mQuestion.get(0).getDifficulty().equals(mQuestion.get(1).getDifficulty())) {
            category = mQuestion.get(0).getDifficulty().toString();
        }
        return category;
    }


    void onBackPressed() {
        if (currentPosition.getValue() != 0){
            currentPosition.setValue(--count);
            Log.e("tag", "onBackPressed: " + currentPosition.getValue()
                    + mQuestion.get(currentPosition.getValue()).getSelectedAnswerPosition());
        }else{
            finishEvent.call();
        }
    }

    void onSkipClick() {
        if (mQuestion.size() >= currentPosition.getValue()) {
            questions.setValue(mQuestion);
            currentPosition.setValue(++count);
            if (currentPosition.getValue() + 1 == mQuestion.size()) {
                finishQuiz();
            }
        }
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {

        if (mQuestion.size() > position && position >= 0) {
            mQuestion.get(position)
                    .setSelectedAnswerPosition(selectedAnswerPosition);


            questions.setValue(mQuestion);

            if (position + 1 == mQuestion.size()) {
                finishQuiz();
            } else {
                currentPosition.setValue(position + 1);
            }
        }
    }


    public void nextPage() {
        currentPosition.setValue(++count);
    }

    public void backPage() {
        currentPosition.setValue(--count);
    }
}
