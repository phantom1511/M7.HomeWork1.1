package com.dastan.m7homework11.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.R;
import com.dastan.m7homework11.core.CoreFragment;
import com.dastan.m7homework11.data.model.History;
import com.dastan.m7homework11.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends CoreFragment implements HistoryAdapter.HistoryListener{

    private TextView result;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private View view;
    private HistoryViewModel historyViewModel;
    private List<History> currentHistories;

    public static HistoryFragment newInstance(){
        return new HistoryFragment();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setRecyclerView(view);
        historyAdapter.setList(new ArrayList<>());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    private void initViews(View view){
        recyclerView = view.findViewById(R.id.rvHistoryFragment);
    }

    private void setRecyclerView(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        historyAdapter = new HistoryAdapter(this);
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        historyViewModel.historyLiveData.observe(getActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                if (histories != null){
                    historyAdapter.setList(histories);
                    currentHistories = histories;
                }
            }
        });

        //mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

//        mainViewModel.counter.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                Log.d("ololo", " history " + integer);
//                result.setText(integer.toString());
//            }
//        });
    }

    private void showPopUp(View v, int position){
        PopupMenu popupMenu = new PopupMenu(getContext(),v);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.delete:
                    QuizApp.quizDatabase.historyDao().deleteById(currentHistories.get(position).getId());
                    Toast.makeText(getActivity(),"delete",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.share:
                    Toast.makeText(getActivity(),"share",Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @Override
    public void onDotsClick(int position, View v) {
        showPopUp(v,position);
    }
}
