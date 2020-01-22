package com.dastan.m7homework11.ui.map;

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


public class MapFragment extends Fragment {
    private MapViewModel mapViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView =root.findViewById(R.id.tvMap);
        mapViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
