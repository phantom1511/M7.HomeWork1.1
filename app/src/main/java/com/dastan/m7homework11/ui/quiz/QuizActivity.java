package com.dastan.m7homework11.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.Question;
import com.dastan.m7homework11.ui.main.MainFragment;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private QuizViewModel quizViewModel;
    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    private int amount;
    private Integer categoty;
    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        setRecyclerView();

        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);
        categoty = getIntent().getIntExtra(EXTRA_CATEGORY, +8);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

        quizViewModel.fetchQuestions(amount, categoty, difficulty);

        quizViewModel.mutableLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                quizAdapter.setList(questions);
            }
        });
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

    public static void start(Context context, Integer amount, Integer category, String difficulty){
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);

        context.startActivity(intent);
    }
}
