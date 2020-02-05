package com.dastan.m7homework11.data.history;

public interface IHistoryStorage {
    void onSave();
    void onDeleteHistory();
    void onDeleteAll();
    void getHistory();
    void getAll();
}
