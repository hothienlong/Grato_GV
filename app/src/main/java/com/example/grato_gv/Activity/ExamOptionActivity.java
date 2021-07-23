package com.example.grato_gv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grato_gv.R;

public class ExamOptionActivity extends AppCompatActivity {

    Toolbar examOptionToolbar;
    CardView keyOption, scanpapersOption, reviewpapersOption, analysisOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_option);

        addControls();
        addEvents();


    }

    private void addEvents() {
        setSupportActionBar(examOptionToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true); // icon

        examOptionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        keyOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent
                Intent intent = getIntent();
                if(intent != null){
                    if(intent.hasExtra("examName")){

                        String examName = intent.getStringExtra("examName");

                        // start new intent
                        Intent newIntent = new Intent(ExamOptionActivity.this, ExamCodeActivity.class);
                        newIntent.putExtra("examName", examName);
                        startActivity(newIntent);

                    }
                }

            }
        });

        scanpapersOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamOptionActivity.this, ViewScanActivity.class);
                startActivity(intent);
            }
        });

        reviewpapersOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent
                Intent intent = getIntent();
                if(intent != null){
                    if(intent.hasExtra("examName")){

                        String examName = intent.getStringExtra("examName");

                        // start new intent
                        Intent newIntent = new Intent(ExamOptionActivity.this, ReviewExamActivity.class);
                        newIntent.putExtra("examName", examName);
                        startActivity(newIntent);

                    }
                }
            }
        });
    }

    private void addControls() {
        examOptionToolbar = findViewById(R.id.examOptionToolbar);
        keyOption = findViewById(R.id.keyOption);
        scanpapersOption = findViewById(R.id.scanpapersOption);
        reviewpapersOption = findViewById(R.id.reviewpapersOption);
        analysisOption = findViewById(R.id.analysisOption);

    }
}