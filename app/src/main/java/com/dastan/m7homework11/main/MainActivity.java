package com.dastan.m7homework11.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.dastan.m7homework11.R;
import com.dastan.m7homework11.ui.main.MainFragment;
import com.dastan.m7homework11.ui.main.MainViewModel;
import com.dastan.m7homework11.ui.history.HistoryFragment;
import com.dastan.m7homework11.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.bottomNavView);
        viewPager = findViewById(R.id.view_pager);

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        setBottomNavView();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void setBottomNavView(){
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_main:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_history:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_settings:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navView.getMenu().findItem(R.id.navigation_main).setChecked(true);
                        break;
                    case 1:
                        navView.getMenu().findItem(R.id.navigation_history).setChecked(true);
                        break;
                    case 2:
                        navView.getMenu().findItem(R.id.navigation_settings).setChecked(true);
                        break;
                }
            }

        });
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
