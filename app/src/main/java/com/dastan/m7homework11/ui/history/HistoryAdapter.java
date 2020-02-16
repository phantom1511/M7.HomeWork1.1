package com.dastan.m7homework11.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final HistoryListener listener;
    private List<History> list = new ArrayList<>();

    public HistoryAdapter(HistoryListener listener) {
        this.listener = listener;
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
        return new HistoryViewHolder(view, listener);
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
        private ImageView dots;
        private HistoryListener listener;

        public HistoryViewHolder(@NonNull View itemView, HistoryListener listener) {
            super(itemView);
            this.listener = listener;
            initViews();

            dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDotsClick(getAdapterPosition(), v);
                }
            });
        }

        private void initViews(){
            dots = itemView.findViewById(R.id.historyDots);
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

    public interface HistoryListener{
        void onDotsClick(int position,View v);
    }
}
