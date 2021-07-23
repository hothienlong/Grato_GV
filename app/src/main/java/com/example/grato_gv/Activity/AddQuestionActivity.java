package com.example.grato_gv.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grato_gv.Adapter.AddQuestionViewPagerAdapter;
import com.example.grato_gv.Adapter.MainViewPagerAdapter;
import com.example.grato_gv.Fragment.AddQuestionFragment;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Question;
import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity  {

    Toolbar addQuestionToolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    LoginResponse loginResponseSession;

    GratoViewModel mGratoViewModel;

    String mQuizName;

    ImageView imgSave;

    ImgSaveQAClickListener mImgSaveQAClickListener;

    AddQuestionActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        catchIntent();
        init();
        addEvents();
    }

    private void catchIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("quizName")){
                mQuizName = intent.getStringExtra("quizName");
            }
        }
    }

    private void init() {
        setSupportActionBar(addQuestionToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true); // icon


        // viewpager & tab layout
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        // show all question answers
        mGratoViewModel.getResponseAllQuestionAnswerQuiz().observe(this, new Observer<List<QuestionAnswer>>() {
            @Override
            public void onChanged(List<QuestionAnswer> questionAnswers) {

                AddQuestionViewPagerAdapter adapter = new AddQuestionViewPagerAdapter(getSupportFragmentManager());

                Integer curQuestion = null;
                if(questionAnswers.size() != 0){
                    curQuestion = questionAnswers.get(0).getQuestion_id();
                }

                List<QuestionAnswer> questionAnswersInOneFragment = new ArrayList<>();
                for(int i = 0; i < questionAnswers.size(); i++) {

                    if(curQuestion != questionAnswers.get(i).getQuestion_id()){
                        // Add a question
//                        for(int j =0; j < questionAnswersInOneFragment.size();j++){
//                            Log.d("LLL", questionAnswersInOneFragment.get(j).getQuestion_content());
//
//                            Log.d("LLL", questionAnswersInOneFragment.get(j).getAnswer_content());
//                        }
                        // Dùng new ArrrayList để ko bị ánh xạ tham chiếu tới lst cũ
                        Log.d("curQuestion", curQuestion.toString());
                        Log.d("questionAnswers", String.valueOf(i));
                        AddQuestionFragment newFragment = new AddQuestionFragment(
                                new ArrayList<>(questionAnswersInOneFragment),
                                context,
                                mQuizName,
                                curQuestion,
                                imgSave
                        );
                        // set listener imgSave
//                        newFragment.setmImgSaveQAClickListener(mImgSaveQAClickListener);
                        // add new fragment
                        adapter.addFragment(newFragment);

                        curQuestion = questionAnswers.get(i).getQuestion_id();

                        questionAnswersInOneFragment.clear();
                    }
                    questionAnswersInOneFragment.add(questionAnswers.get(i));

                    // add last fragment (tổng hợp lại những questionAnswer cuối cùng: chắc chắn có)
                    if(i == questionAnswers.size()-1){
                        Log.d("questionAnswers", String.valueOf(i)  + "last");
                        AddQuestionFragment newFragment = new AddQuestionFragment(
                                new ArrayList<>(questionAnswersInOneFragment),
                                context,
                                mQuizName,
                                curQuestion,
                                imgSave
                        );
                        // set listener imgSave
//                        newFragment.setmImgSaveQAClickListener(mImgSaveQAClickListener);
                        // add new fragment
                        // Add a question
                        adapter.addFragment(newFragment);
                    }
                }


//                // get no_question -> question no answer
//                mGratoViewModel.getResponseAQuiz().observe(context, new Observer<Quiz>() {
//                    @Override
//                    public void onChanged(Quiz quiz) {
//                        for(int i=questionAnswers.size(); i<quiz.getNo_question(); i++){
//
//                        }
//
//
//                    }
//                });
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
//                mGratoViewModel.fetchAQuiz(
//                        loginResponseSession.getToken(),
//                        "CO3005",
//                        202,
//                        mQuizName
//                );
            }
        });

        mGratoViewModel.fetchAllQuestionAnswerQuiz(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mQuizName
        );


    }

    private void addEvents() {
        addQuestionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void addControls() {
        addQuestionToolbar = findViewById(R.id.addQuestionToolbar);
        viewPager = findViewById(R.id.addQuestionViewPager);
        tabLayout = findViewById(R.id.addQuestionTabLayout);
        imgSave = findViewById(R.id.imgSave);
    }

    private void saveQuestionAnswers(){
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set listener
                mImgSaveQAClickListener.onQuestionAnswerSaveClick();
            }
        });
    }




    public interface ImgSaveQAClickListener{
        // AddQuestionActivity sẽ định nghĩa
        void onQuestionAnswerSaveClick();
    }

    public void setmImgSaveQAClickListener(ImgSaveQAClickListener imgSaveQAClickListener){
        mImgSaveQAClickListener = imgSaveQAClickListener;
    }
}