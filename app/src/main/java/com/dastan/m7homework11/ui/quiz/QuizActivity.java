package com.dastan.m7homework11.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.Question;
import com.dastan.m7homework11.ui.main.MainActivity;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.ArrayList;
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
    private TextView progressAmountText;
    private ProgressBar progressBar;
    private TextView titleCategory;
    private List<Question> questions = new ArrayList<>();
    private Button skipBtn;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        initListeners();
        setRecyclerView();

        quizViewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                titleCategory.setText(questions.get(0).getCategory());
            }
        });


        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);
        categoty = getIntent().getIntExtra(EXTRA_CATEGORY, +8);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

        quizAdapter.setList(new ArrayList<>());

        quizViewModel.fetchQuestions(amount, categoty, difficulty);

        quizViewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                quizAdapter.setList(questions);
            }
        });

        getPosition();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvQuizActivity);
        progressAmountText = findViewById(R.id.tvAmountProgress);
        progressBar = findViewById(R.id.progressBarQuiz);
        titleCategory = findViewById(R.id.tvQuizActivity);
        skipBtn = findViewById(R.id.btnSkip);
        backImg = findViewById(R.id.icBack);
    }

    private void initListeners(){
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSkip(v);
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack(v);
            }
        });
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        quizAdapter = new QuizAdapter();
        recyclerView.setAdapter(quizAdapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public static void start(Context context, Integer amount, Integer category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);

        context.startActivity(intent);
    }

    private void getPosition() {
        quizViewModel.position.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer - 1);
                progressAmountText.setText(integer + "/" + amount);
                progressBar.setProgress(integer);
                progressBar.setMax(amount);

                if (questions.size() > 0)
                    titleCategory.setText(questions.get(integer - 1).getCategory());
            }
        });
    }

    public void onSkip(View view) {
        if (progressBar.getProgress() < amount){
            quizViewModel.nextPage();
        } else {
            ResultActivity.start(this);
        }
    }

    public void onBack(View view){
        if (progressBar.getProgress() != 1){
            quizViewModel.backPage();
        } else {
            MainActivity.start(this);
            finish();
        }
    }
}
