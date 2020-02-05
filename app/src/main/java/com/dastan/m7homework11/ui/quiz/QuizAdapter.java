package com.dastan.m7homework11.ui.quiz;

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

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{
    private List<Question> list = new ArrayList<>();

    public QuizAdapter() {
    }

    public void setList(List<Question> list){
        this.list = list;
        notifyDataSetChanged();
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

        private TextView questionText;
        private TextView quizTitle;
        private ProgressBar progressBar;
        private Button btn1;
        private Button btn2;
        private Button btn3;
        private Button btn4;
        private Button btnSkip;


        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            initViews();
        }

        private void initViews(){
            questionText = itemView.findViewById(R.id.tvQuizQuestion);
            progressBar = itemView.findViewById(R.id.progressBarQuiz);
            btn1 = itemView.findViewById(R.id.btnAnswer1);
            btn2 = itemView.findViewById(R.id.btnAnswer2);
            btn3 = itemView.findViewById(R.id.btnAnswer3);
            btn4 = itemView.findViewById(R.id.btnAnswer4);
            btnSkip = itemView.findViewById(R.id.btnSkip);
        }


        public void bind(Question question) {
            questionText.setText(question.getQuestion());
        }
    }
}
