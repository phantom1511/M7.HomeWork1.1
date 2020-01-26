package com.dastan.m7homework11.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.ui.explore.MainFragment;
import com.dastan.m7homework11.ui.map.HistoryViewModel;


public class SettingsFragment extends Fragment {
    private SettingsViewModel settingsViewModel;

    public static SettingsFragment newInstance(){
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
    }
}