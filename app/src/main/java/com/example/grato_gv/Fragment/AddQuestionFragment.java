package com.example.grato_gv.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.AddQuestionActivity;
import com.example.grato_gv.Adapter.AnswerAdapter;
import com.example.grato_gv.Adapter.ExamCodeAdapter;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Question;
import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class AddQuestionFragment extends Fragment implements AddQuestionActivity.ImgSaveQAClickListener {

    View view;
    private List<QuestionAnswer> lstQuestionAnswers;
    Integer position;
    EditText edtQuestion; // <--- lấy question từ activity
    String mQuizName;
    RecyclerView quizAnswerRecyclerview;

    EditText edtAnswerA, edtAnswerB, edtAnswerC, edtAnswerD;

    GratoViewModel mGratoViewModel;

    LoginResponse loginResponseSession;

//    CardView cardViewAddQuestion;

//    AnswerAdapter answerAdapter;

//    AddQuestionActivity.ImgSaveQAClickListener mImgSaveQAClickListener = this;

    AddQuestionActivity addQuestionActivity;

    ImageView imgSave;


    public AddQuestionFragment(List<QuestionAnswer> lstQuestionAnswers, AddQuestionActivity addQuestionActivity, String quiz_name, Integer position, ImageView imgSave) {
        this.lstQuestionAnswers = lstQuestionAnswers;
        this.addQuestionActivity = addQuestionActivity;
        this.mQuizName = quiz_name;
        this.position = position;
        this.imgSave = imgSave;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_question, container, false);
        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        init();
        addEvents();

        return view;
    }

    private void addEvents() {
//        cardViewAddQuestion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lstQuestionAnswers.add(new QuestionAnswer(
//                        position,
//                        lstQuestionAnswers.get(0).getQuestion_content(),
//                        (char)(lstQuestionAnswers.get(lstQuestionAnswers.size()-1).getAnswer_id()+1),
//                        "Default answer",
//                        0));
//                answerAdapter.notifyDataSetChanged();
//            }
//        });


        // modify question
//        mGratoViewModel.modifyQuestionOfQuiz(
//                loginResponseSession.getToken(),
//                "CO3005",
//                202,
//                mQuizName,
//                position,
//                edtQuestion.getText().toString()
//        );
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

//        mGratoViewModel.getResponseModifyQuestionOfQuiz().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
//            @Override
//            public void onChanged(Response<Void> voidResponse) {
//
//            }
//        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGratoViewModel.modifyQuestionOfQuiz(
                loginResponseSession.getToken(),
                "CO3005",
                202,
                mQuizName,
                position,
                edtQuestion.getText().toString()
        );
            }
        });
    }

    private void init() {
        edtQuestion.setText(lstQuestionAnswers.get(0).getQuestion_content());

        edtAnswerA.setText(lstQuestionAnswers.get(0).getAnswer_content());
        edtAnswerB.setText(lstQuestionAnswers.get(1).getAnswer_content());
        edtAnswerC.setText(lstQuestionAnswers.get(2).getAnswer_content());
        edtAnswerD.setText(lstQuestionAnswers.get(3).getAnswer_content());



//        for(int i =0; i < lstQuestionAnswers.size();i++){
//            Log.d("LLL", lstQuestionAnswers.get(i).getQuestion_content());
//            Log.d("LLL", lstQuestionAnswers.get(i).getAnswer_content());
//        }

        // tạo adapter
//        answerAdapter = new AnswerAdapter((ArrayList<QuestionAnswer>) lstQuestionAnswers);
//
//        // performance
//        quizAnswerRecyclerview.setHasFixedSize(true);
//
//        // set adapter cho Recycler View
//        quizAnswerRecyclerview.setAdapter(answerAdapter);



    }

    private void addControls() {
        edtQuestion = (EditText) view.findViewById(R.id.edtQuestion);
//        quizAnswerRecyclerview = view.findViewById(R.id.quizAnswerItemRecyclerview);
//        cardViewAddQuestion = view.findViewById(R.id.cardViewAddQuestion);
        edtAnswerA = view.findViewById(R.id.edtAnswerA);
        edtAnswerB = view.findViewById(R.id.edtAnswerB);
        edtAnswerC = view.findViewById(R.id.edtAnswerC);
        edtAnswerD = view.findViewById(R.id.edtAnswerD);
    }

    @Override
    public void onQuestionAnswerSaveClick() {
                mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

//        mGratoViewModel.modifyQuestionOfQuiz(
//                        loginResponseSession.getToken(),
//                        question.getSub_id(),
//                        question.getSemester_id(),
//                        question.getQuiz_name(),
//                        question.getQuestion_id(),
//                        question.getContent()
//                );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getClass().getName(), "onPause");
    }

}
