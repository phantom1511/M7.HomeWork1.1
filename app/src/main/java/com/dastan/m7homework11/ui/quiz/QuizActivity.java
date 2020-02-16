package com.dastan.m7homework11.ui.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.dastan.m7homework11.ui.quiz.recycler.QuizAdapter;
import com.dastan.m7homework11.ui.quiz.recycler.QuizViewHolder;
import com.dastan.m7homework11.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizViewHolder.Listener {

    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private QuizViewModel quizViewModel;

    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    private int amount;
    private Integer category;
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
        Log.e("ron", "create");

        quizViewModel.finishEvent.observe(this, aVoid -> finish());
        quizViewModel.openResultEvent.observe(this, integer -> ResultActivity.start(this, integer));

        getQuestion();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvQuizActivity);
        progressAmountText = findViewById(R.id.tvAmountProgress);
        progressBar = findViewById(R.id.progressBarQuiz);
        titleCategory = findViewById(R.id.tvQuizActivity);
        skipBtn = findViewById(R.id.btnSkip);
        backImg = findViewById(R.id.icBack);
    }

    private void initListeners() {
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

    private void getQuestion() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, +8);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

        if (category == 0) {
            category = null;
        }

        quizAdapter.setList(new ArrayList<>());
        if (category == 8) category = null;
        quizViewModel.fetchQuestions(amount, category, difficulty);

        quizViewModel.questions.observe(this, list -> {
            questions = list;
            quizAdapter.setQuestions(list);
            getPosition();
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        quizAdapter = new QuizAdapter(this);
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
        quizViewModel.currentPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.scrollToPosition(integer);
                progressAmountText.setText(integer + "/" + amount);
                progressBar.setProgress(integer + 1);
                progressBar.setMax(amount);

                titleCategory.setText(questions.get(integer).getCategory());
//                if (questions.size() > 0)
//                    titleCategory.setText(questions.get(integer - 1).getCategory());
            }
        });
    }

    public void onSkip(View view) {
        if (progressBar.getProgress() < amount) {
            quizViewModel.onSkipClick();
        } else {
            quizViewModel.finishEvent.call();
        }
    }

    public void onBack(View view) {
        if (progressBar.getProgress() != 1) {
            quizViewModel.onBackPressed();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        quizViewModel.onBackPressed();
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        quizViewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}
