package com.dastan.m7homework11.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.R;
import com.dastan.m7homework11.core.CoreFragment;
import com.dastan.m7homework11.ui.main.MainViewModel;


public class SettingsFragment extends CoreFragment {
    private SettingsViewModel settingsViewModel;

    private SettingsViewModel mViewModel;
    private View view;
    private MainViewModel mainViewModel;
    private TextView share,rateUs,feedback,clear;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener(view);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"delete",Toast.LENGTH_LONG).show();
                QuizApp.quizDatabase.historyDao().deleteAll();
            }
        });
    }

    private void initListener(View v){
        share = v.findViewById(R.id.settings_share);
        rateUs = v.findViewById(R.id.settings_rate_us);
        feedback = v.findViewById(R.id.settings_leave_feedback);
        clear = v.findViewById(R.id.settings_clear_history);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }
}
