package com.dastan.m7homework11.ui.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private List<Question> list = new ArrayList<>();
    private QuizViewHolder.Listener listener;

    public QuizAdapter(QuizViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void setList(List<Question> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_quiz, parent, false);
        return new QuizViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setQuestions(List<Question> questions) {
        this.list.clear();
        this.list.addAll(questions);
        notifyDataSetChanged();
    }

}
