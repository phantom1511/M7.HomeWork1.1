package com.dastan.m7homework11.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dastan.m7homework11.QuizApp;
import com.dastan.m7homework11.R;
import com.dastan.m7homework11.main.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private RecyclerView recyclerView;
    private static QuizAdapter adapter;
    private int positionRV;
    private static List<QuizApp> listExplore;
    private static List<QuizApp> listMap;
    private static List<QuizApp> listProfile;
    private int index;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = root.findViewById(R.id.rvPagerExplore);
        initList();

        return root;
    }

    private void initList() {
        listExplore = new ArrayList<>();
        listMap = new ArrayList<>();
        listProfile = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        switch (index) {
            case 1:
                listExplore.add(new QuizApp());
                recyclerView.setAdapter(new QuizAdapter(listExplore));
                break;
            case 2:
                listMap.add(new QuizApp());
                recyclerView.setAdapter(new QuizAdapter(listMap));
                break;
            case 3:
                listProfile.add(new QuizApp());
                recyclerView.setAdapter(new QuizAdapter(listProfile));
                break;
        }
    }
}