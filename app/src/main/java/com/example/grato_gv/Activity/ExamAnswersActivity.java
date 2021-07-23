package com.example.grato_gv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.grato_gv.Adapter.ExamAnswersAdapter;
import com.example.grato_gv.R;

public class ExamAnswersActivity extends AppCompatActivity {

    Toolbar answersToolbar;
    TextView tvExamCodeId;
    RecyclerView examAnswersRecyclerview;
//    ListView quizScanAnswersListview;

    String mExamCodeId, mKeyAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_answers);

        addControls();
        catchIntent();
        addEvents();
    }

    private void catchIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("examCodeId")){
                mExamCodeId = intent.getStringExtra("examCodeId");
            }
            if(intent.hasExtra("keyAnswer")){
                mKeyAnswer = intent.getStringExtra("keyAnswer");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        getData();
    }

    private void getData() {

        // tạo adapter
        ExamAnswersAdapter examAnswersAdapter = new ExamAnswersAdapter(mKeyAnswer);

        // performance
        examAnswersRecyclerview.setHasFixedSize(true);

        // set adapter cho Recycler View
        examAnswersRecyclerview.setAdapter(examAnswersAdapter);


        tvExamCodeId.setText(mExamCodeId);

    }

    private void addEvents() {
        setSupportActionBar(answersToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true); // icon

        answersToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        answersToolbar = findViewById(R.id.answersToolbar);
        tvExamCodeId = findViewById(R.id.tvExamCodeId);

        examAnswersRecyclerview = findViewById(R.id.examAnswersRecyclerview);
    }
}