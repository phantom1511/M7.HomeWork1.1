package com.dastan.m7homework11.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.main.QuizActivity;


public class MainFragment extends Fragment {

    Button btnOne, btnTwo;

    public static MainFragment newInstance(){
        return new MainFragment();
    }


    private MainViewModel mainViewModel;
    private Button startBtn;
    private View view;
    private SeekBar seekBar;
    private TextView questionAmount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews();
        initListeners();
//        btnOne = view.findViewById(R.id.btnOne);
//        btnTwo = view.findViewById(R.id.btnTwo);
//
//        btnOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainViewModel.increment();
//            }
//        });
//
//        btnTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainViewModel.decrement();
//            }
//        });
        return view;
    }

    private void initViews(){
        startBtn = view.findViewById(R.id.btnStart);
        questionAmount = view.findViewById(R.id.tvNum);
        seekBar = view.findViewById(R.id.seekBar);
    }

    private void initListeners(){
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        questionAmount.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                questionAmount.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

}