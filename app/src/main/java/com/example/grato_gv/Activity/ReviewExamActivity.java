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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.grato_gv.Adapter.ExamCodeAdapter;
import com.example.grato_gv.Adapter.ReviewExamAdapter;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.ExamReview;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Student;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ReviewExamActivity extends AppCompatActivity implements ReviewExamAdapter.ExamReviewItemClickListener {

    Toolbar toolbar;
    RecyclerView reviewExamItemRecyclerview;

    LoginResponse loginResponseSession;

    GratoViewModel mGratoViewModel;

    String mExamName;

    ReviewExamAdapter.ExamReviewItemClickListener mExamReviewItemClickListener = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_exam);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        catchIntent();
        addEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getData();
    }

    private void catchIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("examName")){

                mExamName = intent.getStringExtra("examName");

            }
        }
    }

    private void getData() {
//        ArrayList<ExamReview> lstExamReview = new ArrayList<>();
//        lstExamReview.add(new ExamReview(
//                new Student("Đỗ Mỹ Linh", "1812209"),
//                new ExamCode("001", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("Nguyễn Xuân Tiến", "1814523"),
//                new ExamCode("002", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("H'Hen Niê", "1814456"),
//                new ExamCode("003", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("Cao Xuân Tài", "1812322"),
//                new ExamCode("001", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("Đỗ Mỹ Linh", "1812209"),
//                new ExamCode("001", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("Nguyễn Xuân Tiến", "1814523"),
//                new ExamCode("002", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("H'Hen Niê", "1814456"),
//                new ExamCode("003", null),
//                9.00)
//        );
//        lstExamReview.add(new ExamReview(
//                new Student("Cao Xuân Tài", "1812322"),
//                new ExamCode("001", null),
//                9.00)
//        );

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseAllExamReviews().observe(this, new Observer<List<ExamReview>>() {
            @Override
            public void onChanged(List<ExamReview> examReviews) {
                Log.d(getClass().getName(), String.valueOf(examReviews.size()));
                // tạo adapter
                ReviewExamAdapter reviewExamAdapter = new ReviewExamAdapter((ArrayList<ExamReview>) examReviews);
                reviewExamAdapter.setmExamReviewClickListener(mExamReviewItemClickListener);

                // performance
                reviewExamItemRecyclerview.setHasFixedSize(true);

                // set adapter cho Recycler View
                reviewExamItemRecyclerview.setAdapter(reviewExamAdapter);
            }
        });


        mGratoViewModel.fetchAllExamReviews(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mExamName
        );


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
        toolbar = findViewById(R.id.reviewExamToolbar);
        reviewExamItemRecyclerview = findViewById(R.id.reviewExamItemRecyclerview);
    }

    @Override
    public void onDeleteExamReviewClick(ExamReview examReview) {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        // Delete exam
                        mGratoViewModel.getResponseDeleteExamReview().observe(ReviewExamActivity.this, new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                if(voidResponse.code() == 200){

                                    updateListExamReviews();

                                    Toast.makeText(ReviewExamActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    Toast.makeText(ReviewExamActivity.this, "Delete fail! Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        mGratoViewModel.deleteExamReview(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                mExamName,
                                examReview.getExam_code_id(),
                                examReview.getStudent_id()
                        );

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    private void updateListExamReviews(){
        mGratoViewModel.fetchAllExamReviews(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mExamName
        );
    }
}