package com.dastan.m7homework11.core;

public interface ICoreCallback<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
}
