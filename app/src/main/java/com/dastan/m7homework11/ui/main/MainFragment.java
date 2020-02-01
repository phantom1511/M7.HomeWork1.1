package com.dastan.m7homework11.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.ui.quiz.QuizActivity;

import org.angmarch.views.NiceSpinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainFragment extends Fragment {

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews();
        initListeners();
        setSpinner(getResources().getStringArray(R.array.categories_list), spinnerCategory);
        setSpinner(getResources().getStringArray(R.array.difficult_list), spinnerDifficulty);



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

    private void initViews() {
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
                sentData();
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

    private void setSpinner(String[] array, NiceSpinner niceSpinner) {

        List<String> list = new LinkedList<>(Arrays.asList(array));
        niceSpinner.attachDataSource(list);
//        categoryList = new ArrayList<>();
//        difficultyList = new ArrayList<>();
//
//
//
//        categoryList.add(getResources().getStringArray(R.array.categories_list));
//        categoryList.add("Science");
//        categoryList.add("Sport");
//        categoryList.add("Cars");
//        categoryList.add("Math");
//        categoryList.add("Geographic");
//
//        difficultyList.add("All");
//        difficultyList.add("Easy");
//        difficultyList.add("Medium");
//        difficultyList.add("Hard");
//
//        ArrayAdapter<String> adapterCategory;
//        adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, categoryList );
//        ArrayAdapter<String> adapterDifficulty;
//        adapterDifficulty = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, difficultyList );
//
//        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCategory.setAdapter(adapterCategory);
//        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerDifficulty.setAdapter(adapterDifficulty);
//
//        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (parent.getItemAtPosition(position).equals("Choose Category")){
//
//                }else {
//                    String item = parent.getItemAtPosition(position).toString();
//
//                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (parent.getItemAtPosition(position).equals("Choose Category")){
//
//                }else {
//                    String item = parent.getItemAtPosition(position).toString();
//
//                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void sentData(){
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
        QuizActivity.start(getContext(), amount, category, difficulty);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }


}