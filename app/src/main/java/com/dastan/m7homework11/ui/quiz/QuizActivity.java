package com.dastan.m7homework11.ui.quiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;

public class QuizActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        setRecyclerView();

    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvQuizActivity);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        quizAdapter = new QuizAdapter();
        recyclerView.setAdapter(quizAdapter);
    }
}
