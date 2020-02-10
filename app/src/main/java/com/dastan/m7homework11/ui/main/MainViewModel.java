package com.dastan.m7homework11.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.data.remote.IQuizApiClient;
import com.dastan.m7homework11.data.remote.QuizCountResponse;
import com.dastan.m7homework11.data.remote.QuizGlobalResponse;
import com.dastan.m7homework11.data.model.Categoty;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>();
    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;
    public MutableLiveData<List<Categoty>> categories = new MutableLiveData<>();
    public MutableLiveData<QuizCountResponse> count = new MutableLiveData<QuizCountResponse>();
    public MutableLiveData<QuizGlobalResponse> global = new MutableLiveData<QuizGlobalResponse>();

    public MainViewModel() {

    }

    public void getCategory() {
        quizApiClient.getCategory(new IQuizApiClient.CategoryCallback() {
            @Override
            public void onSuccess(List<Categoty> result) {
                categories.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getCount(Integer integer) {
        quizApiClient.getCount(integer, new IQuizApiClient.CountCallback() {
            @Override
            public void onSuccess(QuizCountResponse result) {
                count.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getGlobal() {
        quizApiClient.getGlobal(new IQuizApiClient.GlobalCallback() {
            @Override
            public void onSuccess(QuizGlobalResponse result) {
                global.setValue(result);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
