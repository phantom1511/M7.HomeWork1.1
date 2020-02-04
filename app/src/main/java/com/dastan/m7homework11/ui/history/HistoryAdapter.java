package com.dastan.m7homework11.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<String> list = new ArrayList<>();

    public HistoryAdapter() {
    }

    public void setList(List<String> list) {
        this.list = list;

        list.add("history1");
        list.add("history2");
        list.add("history3");
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

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(String s) {

        }
    }
}
