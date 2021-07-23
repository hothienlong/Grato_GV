package com.example.grato_gv.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.example.grato_gv.Adapter.MainViewPagerAdapter;
import com.example.grato_gv.Fragment.ClassFragment;
import com.example.grato_gv.Fragment.CreateQuizFragment;
import com.example.grato_gv.Fragment.ExamFragment;
import com.example.grato_gv.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        init();
        addEvents();
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        // viewpager & tab layout
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClassFragment());
        adapter.addFragment(new CreateQuizFragment());
        adapter.addFragment(new ExamFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.long_ic_class_tab_layout);
        tabLayout.getTabAt(1).setIcon(R.drawable.long_ic_create_quiz_tablayout);
        tabLayout.getTabAt(2).setIcon(R.drawable.long_ic_quiz_scan_tab_layout);


        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true); // icon
    }

    private void addControls() {
        toolbar = findViewById(R.id.mainToolbar);
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}