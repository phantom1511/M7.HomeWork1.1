package com.dastan.m7homework11.ui.quiz.recycler;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.EType;
import com.dastan.m7homework11.data.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView questionText;
    private TextView quizTitle;
    private ProgressBar progressBar;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btnSkip;
    private Button exBtn1;
    private Button exBtn2;
    private Listener listener;
    private int position;
    private Question question;

    private ConstraintLayout multyBtnContainer;
    private ConstraintLayout btnContainer;

    public QuizViewHolder(@NonNull View view, Listener listener) {
        super(view);
        this.listener = listener;
        initViews();
        initListeners();
    }

    private void initViews() {
        questionText = itemView.findViewById(R.id.tvQuizQuestion);
        progressBar = itemView.findViewById(R.id.progressBarQuiz);
        btn1 = itemView.findViewById(R.id.btnAnswer1);
        btn2 = itemView.findViewById(R.id.btnAnswer2);
        btn3 = itemView.findViewById(R.id.btnAnswer3);
        btn4 = itemView.findViewById(R.id.btnAnswer4);
        btnSkip = itemView.findViewById(R.id.btnSkip);
        exBtn1 = itemView.findViewById(R.id.btnExtra1);
        exBtn2 = itemView.findViewById(R.id.btnExtra2);

        multyBtnContainer = itemView.findViewById(R.id.btnContainerMulti);
        btnContainer = itemView.findViewById(R.id.btnContainer);
    }

    private void initListeners() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 0);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 1);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 2);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 3);
            }
        });

        exBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 0);
            }
        });

        exBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAnswerClick(position, 1);
            }
        });
    }



    public void bind(Question question, int position) {
        this.position = position;
        this.question = question;
        questionText.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType() == EType.MULTIPLE){
            btnContainer.setVisibility(View.INVISIBLE);
            multyBtnContainer.setVisibility(View.VISIBLE);
            btn1.setText(Html.fromHtml(question.getAnswers().get(0)));
            btn2.setText(Html.fromHtml(question.getAnswers().get(1)));
            btn3.setText(Html.fromHtml(question.getAnswers().get(2)));
            btn4.setText(Html.fromHtml(question.getAnswers().get(3)));
        }else {
            exBtn1.setText(question.getAnswers().get(0));
            exBtn2.setText(question.getAnswers().get(1));
        }

    }



    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
