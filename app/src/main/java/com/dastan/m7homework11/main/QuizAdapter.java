package com.dastan.m7homework11.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.R;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{
    private List<QuizApp> list;

    public QuizAdapter(List<QuizApp> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class QuizViewHolder extends RecyclerView.ViewHolder{

        private TextView timeText;
        private TextView lessonText;
        private TextView typeText;
        private TextView roomText;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(QuizApp quizApp) {

        }
    }
}