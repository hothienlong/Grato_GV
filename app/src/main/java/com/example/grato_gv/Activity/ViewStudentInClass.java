package com.example.grato_gv.Activity;

import android.os.Bundle;

import com.example.grato_gv.Adapter.MainViewPagerAdapter;
import com.example.grato_gv.Fragment.QuizInClassFragment;
import com.example.grato_gv.Fragment.SettingAttendanceFragment;
import com.example.grato_gv.Fragment.StudentInClass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.example.grato_gv.R;
import com.google.android.material.tabs.TabLayout;

public class ViewStudentInClass extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_in_class);
        addControls();
        init();

    }


    private void init() {
        // viewpager & tab layout
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StudentInClass());
        adapter.addFragment(new SettingAttendanceFragment());
        adapter.addFragment(new QuizInClassFragment());


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.thong_icon_group);
        tabLayout.getTabAt(1).setIcon(R.drawable.thong_icon_grade);
        tabLayout.getTabAt(2).setIcon(R.drawable.thong_icon_attendance);


        Toolbar toolbar = findViewById(R.id.toolbar_11);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true); // icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        tabLayout = findViewById(R.id.tabLayoutInClass);
        viewPager = findViewById(R.id.viewPager_11);
    }
}