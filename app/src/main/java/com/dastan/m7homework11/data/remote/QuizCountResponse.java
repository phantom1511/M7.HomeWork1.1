package com.dastan.m7homework11.data.remote;

import com.dastan.m7homework11.data.model.QuestionCount;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class QuizCountResponse {
    @SerializedName("category_id")
    private Integer categoryId;

    @SerializedName("category_question_count")
    private QuestionCount categoryQuestionCount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public QuestionCount getCategoryQuestionCount() {
        return categoryQuestionCount;
    }

    public void setCategoryQuestionCount(QuestionCount categoryQuestionCount) {
        this.categoryQuestionCount = categoryQuestionCount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
