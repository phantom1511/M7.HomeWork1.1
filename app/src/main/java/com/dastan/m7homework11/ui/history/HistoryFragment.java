package com.dastan.m7homework11.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.ui.main.MainViewModel;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    private TextView result;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private View view;

    public static HistoryFragment newInstance(){
        return new HistoryFragment();
    }

    private HistoryViewModel historyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);

//        result = view.findViewById(R.id.tvHistory);

        setRecyclerView();
        historyAdapter.setList(new ArrayList<>());

        return view;
    }

    private void initViews(){
        recyclerView = view.findViewById(R.id.rvHistoryFragment);
    }

    private void setRecyclerView() {
        initViews();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        historyAdapter = new HistoryAdapter();
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        //mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

//        mainViewModel.counter.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                Log.d("ololo", " history " + integer);
//                result.setText(integer.toString());
//            }
//        });
    }
}
