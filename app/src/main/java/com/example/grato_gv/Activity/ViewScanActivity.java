package com.example.grato_gv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.grato_gv.R;

public class ViewScanActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvExamCodeId, tvGrading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scan);

        addControls();
        addEvents();
    }

    private void addEvents() {
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
        toolbar = findViewById(R.id.viewScanToolbar);
        tvExamCodeId = findViewById(R.id.tvExamCodeId);
        tvGrading = findViewById(R.id.tvGrading);
    }
}