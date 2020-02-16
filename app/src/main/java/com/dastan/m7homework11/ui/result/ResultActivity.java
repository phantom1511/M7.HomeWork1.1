package com.dastan.m7homework11.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.R;

public class ResultActivity extends AppCompatActivity {

    private static String RESULT_ID = "result_id";
    private Integer id;
    private ResultViewModel resultViewModel;
    private TextView resultDifficulty, correctAnswers, resultP;
    private Button finishBtn;

    public static void start(Context context, Integer integer) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(RESULT_ID, integer));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        id = getIntent().getIntExtra(RESULT_ID, 0);

        initViews();
        initListeners();
        resultViewModel.getResult(id);
        setViewContent();
    }

    private void initViews() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultDifficulty = findViewById(R.id.difficultyHistory);
        correctAnswers = findViewById(R.id.correctAnswer);
        resultP = findViewById(R.id.ResultCard);
        finishBtn = findViewById(R.id.btnFinish);
    }

    private void initListeners() {
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setViewContent() {
        resultViewModel.quizResult.observe(this, quizResult -> {
            resultDifficulty.setText(quizResult.getDifficulty());
            correctAnswers.setText(quizResult.getCorrectAnswersAmount() + " / " + quizResult.getQuestions().size());
            int correctAnswersPersent = (int) ((double) quizResult.getCorrectAnswersAmount() / quizResult.getQuestions().size() * 100);
            resultP.setText(correctAnswersPersent + " %");
        });
    }
}
