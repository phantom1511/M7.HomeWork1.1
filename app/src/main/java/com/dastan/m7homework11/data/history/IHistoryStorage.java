package com.dastan.m7homework11.data.history;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.QuizResult;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.List;

@Dao
public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();
    LiveData<List<History>> getAllHistory();

    void delete(int id);

    void deleteAll();
//    @Insert
//    void save(QuizResult result);
//
//    @Delete
//    void delete(QuizResult result);
//
//    @Query("DELETE FROM quizresult")
//    void deleteAll();
//
//    @Query("SELECT * FROM quizresult WHERE id=:id")
//    QuizResult get(int id);
//
//    @Query("SELECT * FROM QuizResult")
//    LiveData<List<QuizResult>> getAll();
//
//    int saveQuizResult(QuizResult result);
}
