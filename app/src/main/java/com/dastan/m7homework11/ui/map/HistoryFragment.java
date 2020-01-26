package com.dastan.m7homework11.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.ui.explore.MainFragment;
import com.dastan.m7homework11.ui.explore.MainViewModel;


public class HistoryFragment extends Fragment {

    private TextView result;
    private MainViewModel mainViewModel;

    public static HistoryFragment newInstance(){
        return new HistoryFragment();
    }

    private HistoryViewModel historyViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        result = view.findViewById(R.id.tvMap);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        mainViewModel.counter.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("ololo", " history " + integer);
                result.setText(integer.toString());
            }
        });
    }
}
