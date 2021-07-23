package com.example.grato_gv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grato_gv.Adapter.ExamCodeAdapter;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ExamCodeActivity extends AppCompatActivity implements ExamCodeAdapter.ExamCodeItemClickListener {

    Toolbar toolbar;
    RecyclerView examCodeRecyclerview;

    LoginResponse loginResponseSession;


    GratoViewModel mGratoViewModel;

    ExamCodeAdapter.ExamCodeItemClickListener mExamCodeItemClickListener = this;

    String mExamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_code);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        catchIntent();
        addEvents();

    }

    private void catchIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("examName")){

                mExamName = intent.getStringExtra("examName");

            }
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
//        ArrayList<ExamCode> lstExamCode = new ArrayList<>();
//        lstExamCode.add(new ExamCode("001", null));
//        lstExamCode.add(new ExamCode("002", null));
//        lstExamCode.add(new ExamCode("003", null));
//        lstExamCode.add(new ExamCode("004", null));

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseAllExamCodes().observe(this, new Observer<List<ExamCode>>() {
            @Override
            public void onChanged(List<ExamCode> examCodes) {
                // tạo adapter
                ExamCodeAdapter examCodeAdapter = new ExamCodeAdapter((ArrayList<ExamCode>) examCodes);
                examCodeAdapter.setmExamCodeClickListener(mExamCodeItemClickListener);

                // performance
                examCodeRecyclerview.setHasFixedSize(true);

                // set adapter cho Recycler View
                examCodeRecyclerview.setAdapter(examCodeAdapter);
            }
        });


        mGratoViewModel.fetchAllExamCodes(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mExamName
        );


    }

    private void addControls() {
        examCodeRecyclerview = findViewById(R.id.examCodeItemRecyclerview);
        toolbar = findViewById(R.id.examCodeToolbar);
    }

    @Override
    public void onDeleteExamCodeClick(ExamCode examCode) {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        // Delete exam
                        mGratoViewModel.getResponseDeleteExamCode().observe(ExamCodeActivity.this, new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                if(voidResponse.code() == 200){

                                    updateListExamCodes();

                                    Toast.makeText(ExamCodeActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    Toast.makeText(ExamCodeActivity.this, "Delete fail! Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        mGratoViewModel.deleteExamCode(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                mExamName,
                                examCode.getExam_code_id()
                        );

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    private void updateListExamCodes(){
        mGratoViewModel.fetchAllExamCodes(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mExamName
        );
    }
}
