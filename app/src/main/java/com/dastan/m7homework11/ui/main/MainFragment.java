package com.dastan.m7homework11.ui.main;


import android.os.Bundle;
import android.util.Log;
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
import com.dastan.m7homework11.core.CoreFragment;
import com.dastan.m7homework11.ui.quiz.QuizActivity;
import com.dastan.m7homework11.utils.SimpleSeekBarChangeListener;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainFragment extends CoreFragment {

    Button btnOne, btnTwo;

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    private MainViewModel mainViewModel;
    private Button startBtn;
    private View view;
    private SeekBar seekBar;
    private TextView questionAmount;
    private NiceSpinner spinnerCategory, spinnerDifficulty;
    private List<String> categoryList, difficultyList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
        setSpinner(getResources().getStringArray(R.array.categories_list), spinnerCategory);
        setSpinner(getResources().getStringArray(R.array.difficult_list), spinnerDifficulty);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    private void initViews(View view) {
        startBtn = view.findViewById(R.id.btnStart);
        questionAmount = view.findViewById(R.id.tvNum);
        seekBar = view.findViewById(R.id.seekBar);
        spinnerCategory = view.findViewById(R.id.spCategory);
        spinnerDifficulty = view.findViewById(R.id.spDifficulty);

    }

    private void initListeners() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), QuizActivity.class);
//                startActivity(intent);
                startQuiz();

            }
        });

        questionAmount.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                questionAmount.setText(String.valueOf(progress));
            }
        });
    }

    private void setSpinner(String[] array, NiceSpinner niceSpinner) {

        List<String> list = new LinkedList<>(Arrays.asList(array));
        niceSpinner.attachDataSource(list);
    }

    private void startQuiz(){
        int amount = seekBar.getProgress();
        Integer category = spinnerCategory.getSelectedIndex() + 8;
        String difficulty = null;

        switch (spinnerDifficulty.getSelectedIndex()) {
            case 1:
                difficulty = null;
                break;
            case 2:
                difficulty = "easy" ;
                break;
            case 3:
                difficulty = "medium";
                break;
            case 4:
                difficulty = "hard";
                break;
        }

        if (category < 9){
            category = null;
        } else {
            category = spinnerCategory.getSelectedIndex() + 8;
        }
        QuizActivity.start(getActivity(), amount, category, difficulty);
        Log.e("ron", amount + " " + category + " " + difficulty);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }


}