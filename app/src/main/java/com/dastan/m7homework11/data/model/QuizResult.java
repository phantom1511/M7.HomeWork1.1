package com.dastan.m7homework11.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dastan.m7homework11.data.db.QuestionsConverter;
import com.dastan.m7homework11.data.db.TimestampConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "quiz_result")
public class QuizResult {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "questions")
    @TypeConverters({QuestionsConverter.class})
    private List<Question> questions;
    @ColumnInfo(name = "correct_answer_amount")
    private int correctAnswersAmount;
    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    public QuizResult(int id, String category, String difficulty, List<Question> questions, int correctAnswersAmount, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswersAmount = correctAnswersAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswersAmount() {
        return correctAnswersAmount;
    }

    public void setCorrectAnswersAmount(int correctAnswersAmount) {
        this.correctAnswersAmount = correctAnswersAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
