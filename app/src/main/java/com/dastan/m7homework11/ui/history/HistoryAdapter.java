package com.dastan.m7homework11.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> list = new ArrayList<>();

    public HistoryAdapter() {
    }

    public void setList(List<History> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory,tvCorrectAnswers,tvDifficulty,tvDate;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews(){
            tvCategory = itemView.findViewById(R.id.historyCategory);
            tvCorrectAnswers = itemView.findViewById(R.id.correctAnswer);
            tvDifficulty = itemView.findViewById(R.id.difficultyHistory);
            tvDate = itemView.findViewById(R.id.dateHistory);
        }

        public void bind(History history) {
            tvCategory.setText(history.getCategory());
            tvDifficulty.setText(history.getDifficulty());
            tvCorrectAnswers.setText(history.getCorrectAnswers() + " / " + history.getAmount());
            tvDate.setText(history.getCreatedAt().toString());
        }
    }
}
