package com.dastan.m7homework11.data.remote;

import com.dastan.m7homework11.model.Categoty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizCategoryResponse {
    @SerializedName("trivia_categories")
    private List<Categoty> triviaCategories;

    public List<Categoty> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<Categoty> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }
}
