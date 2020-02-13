package com.dastan.m7homework11.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dastan.m7homework11.data.history.HistoryDao;
import com.dastan.m7homework11.data.model.QuizResult;

@Database(entities = {QuizResult.class}, version = 1, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
