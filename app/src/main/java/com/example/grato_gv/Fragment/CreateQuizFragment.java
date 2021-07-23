package com.example.grato_gv.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.example.grato_gv.Activity.LoginActivity;
import com.example.grato_gv.Activity.TruongListSubjectActivity;
import com.example.grato_gv.Adapter.CreateQuizAdapter;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.Model.User;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Response;


public class CreateQuizFragment extends Fragment implements CreateQuizAdapter.QuizItemClickListener {

    View view;
    FloatingActionButton fabAdd;
    RecyclerView quizItemRecyclerview;
    CreateQuizAdapter createQuizAdapter;

    CreateQuizAdapter.QuizItemClickListener mQuizItemClickListener = this;

    CoordinatorLayout createQuizViewGroup;
    SearchView searchView;

    GratoViewModel mGratoViewModel;

    LoginResponse loginResponseSession;

    CreateQuizFragment context = this;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_quiz, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        getData();
        addEvents();

        return view;
    }

//    String mDeadLine = "";

    private void addEvents() {
        // fabAdd quiz
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = getLayoutInflater().inflate(R.layout.layout_popup_add_new_quiz, null);

                AlertDialog alertDialog =
                        new AlertDialog.Builder(getContext())
                                .setView(popUpView)
                                .setCancelable(false)
                                .create();
                alertDialog.show();

                // Spinner number quiz
                Spinner spinnerNumberQuiz = popUpView.findViewById(R.id.spinnerNumberQuiz);
                ArrayAdapter<String> adapterNumberQuiz = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item
                );
                adapterNumberQuiz.add("10 questions");
                adapterNumberQuiz.add("15 questions");
                adapterNumberQuiz.add("20 questions");
                adapterNumberQuiz.add("25 questions");

                adapterNumberQuiz.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinnerNumberQuiz.setAdapter(adapterNumberQuiz);

                // Spinner max time
                Spinner spinnerMaxTime = popUpView.findViewById(R.id.spinnerMaxTime);
                ArrayAdapter<String> adapterMaxTime = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item
                );
                adapterMaxTime.add("5 minutes");
                adapterMaxTime.add("10 minutes");
                adapterMaxTime.add("15 minutes");
                adapterMaxTime.add("45 minutes");
                adapterMaxTime.add("60 minutes");
                adapterMaxTime.add("90 minutes");

                adapterMaxTime.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinnerMaxTime.setAdapter(adapterMaxTime);


                // Deadline
                TextView tvDeadline = popUpView.findViewById(R.id.tvDeadline);
                ImageView imgDeadline = popUpView.findViewById(R.id.imgDeadline);
                Calendar calendar = Calendar.getInstance();

                imgDeadline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        tvDeadline.setText(String.format("%d-%d-%d %d:%d %s",
                                                dayOfMonth,
                                                month+1,
                                                year,
                                                (hourOfDay < 12) ? hourOfDay : hourOfDay-12,
                                                minute,
                                                (hourOfDay < 12) ? "AM" : "PM"
                                                )
                                        );


                                        String mDeadLine = String.format("%d-%d-%d %d:%d:%d",
                                                year,
                                                month+1,
                                                dayOfMonth,
                                                hourOfDay,
                                                minute,
                                                hourOfDay
                                        );

                                        addNewQuiz(popUpView, spinnerMaxTime, spinnerNumberQuiz, mDeadLine, alertDialog);
                                    }
                                };

                                TimePickerDialog timePickerDialog = new TimePickerDialog(
                                        getContext(),
                                        timeSetListener,
                                        calendar.get(Calendar.HOUR),
                                        calendar.get(Calendar.MINUTE), // current hour, minute
                                        DateFormat.is24HourFormat(getContext())
                                );
                                timePickerDialog.show();
                            }
                        };
                        Calendar calendarDeadline = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                getContext(),
                                dateSetListener,
                                calendarDeadline.get(Calendar.YEAR),
                                calendarDeadline.get(Calendar.MONTH),
                                calendarDeadline.get(Calendar.DAY_OF_MONTH) // current year, month, day
                        );
                        datePickerDialog.show();
                    }
                });


                // Cancel
                popUpView.findViewById(R.id.cardViewCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                // Ok
                popUpView.findViewById(R.id.cardViewOK).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Please input the deadline!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        // Set text searchview color.White
        TextView textView = (TextView) searchView.findViewById(R.id.search_src_text);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.WHITE);

    }

    private void addNewQuiz(View popUpView, Spinner spinnerMaxTime, Spinner spinnerNumberQuiz, String mDeadLine, AlertDialog alertDialog){
        if(mDeadLine != ""){
            popUpView.findViewById(R.id.cardViewOK).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText edtQuizName = popUpView.findViewById(R.id.edtQuizName);

                    String maxTimeStr = spinnerMaxTime.getSelectedItem().toString();
                    Integer maxTime = Integer.parseInt(maxTimeStr.split("\\s")[0]);//splits the string based on whitespace

                    String numQuizStr = spinnerNumberQuiz.getSelectedItem().toString();
                    Integer numQuiz = Integer.parseInt(numQuizStr.split("\\s")[0]);//splits the string based on whitespace

                    Log.d("deadlineQuiz", mDeadLine);

                    // Add quiz
                    mGratoViewModel.getResponseAddQuiz().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
                        @Override
                        public void onChanged(Response<Void> voidResponse) {
                            if(voidResponse.code() == 200) {
                                updateListQuiz();
                                Toast.makeText(getActivity(), "Add quiz successful!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getActivity(), "Add quiz fail! Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    String quizName = edtQuizName.getText().toString();

                    mGratoViewModel.addQuiz(
                            loginResponseSession.getToken(),
                            "CO3005",
                            202,
                            quizName,
                            maxTime,
                            numQuiz,
                            mDeadLine
                    );

                    alertDialog.dismiss();


                    // Add question to this quiz (no content & 1 answer right)
                    for(int i=1; i<=numQuiz; i++){
                        int finalI = i;
                        mGratoViewModel.getResponseAddQuestionToQuiz().observe(context, new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                // add answer default

                                mGratoViewModel.getResponseAddAnswerToQuestion().observe(context, new Observer<Response<Void>>() {
                                    @Override
                                    public void onChanged(Response<Void> voidResponse) {

                                    }
                                });

                                mGratoViewModel.addAnswerToQuestion(
                                        loginResponseSession.getToken(),
                                        "CO3005",
                                        202,
                                        quizName,
                                        finalI,
                                        "A",
                                        1,
                                        "Default answer"
                                );

                                mGratoViewModel.addAnswerToQuestion(
                                        loginResponseSession.getToken(),
                                        "CO3005",
                                        202,
                                        quizName,
                                        finalI,
                                        "B",
                                        0,
                                        "Default answer"
                                );

                                mGratoViewModel.addAnswerToQuestion(
                                        loginResponseSession.getToken(),
                                        "CO3005",
                                        202,
                                        quizName,
                                        finalI,
                                        "C",
                                        0,
                                        "Default answer"
                                );

                                mGratoViewModel.addAnswerToQuestion(
                                        loginResponseSession.getToken(),
                                        "CO3005",
                                        202,
                                        quizName,
                                        finalI,
                                        "D",
                                        0,
                                        "Default answer"
                                );
                            }
                        });

                        mGratoViewModel.addQuestionToQuiz(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                quizName,
                                i,
                                ""
                        );


                    }
                }
            });
        }
//        else {
//            popUpView.findViewById(R.id.cardViewOK).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(), "Please input the deadline!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    private void getData() {
        // Khai báo data giả cho mảng
//        ArrayList<Quiz> lstQuiz = new ArrayList<>();
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));
//        lstQuiz.add(new Quiz("Quiz 6: Abstract syntax tree", "Deadline: 14 April 2021 12:10 PM", 10, 20));

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseAllQuizs().observe(getViewLifecycleOwner(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                Log.d("quizzz", quizzes.size()+"");
                // tạo adapter
                createQuizAdapter = new CreateQuizAdapter((ArrayList<Quiz>) quizzes);
                createQuizAdapter.setmQuizClickListener(mQuizItemClickListener);
                // performance
                quizItemRecyclerview.setHasFixedSize(true);
                // set adapter cho Recycler View
                quizItemRecyclerview.setAdapter(createQuizAdapter);
            }
        });


        mGratoViewModel.fetchAllQuizs(loginResponseSession.getToken(), "CO3005", 202);

    }



    private void addControls() {
        fabAdd = view.findViewById(R.id.fabAdd);
        quizItemRecyclerview = view.findViewById(R.id.quizItemRecyclerview);
        createQuizViewGroup = view.findViewById(R.id.createQuizViewGroup);
        searchView = view.findViewById(R.id.searchQuizName);
    }

    @Override
    public void onDeleteQuizClick(Quiz quiz) {

        new AlertDialog.Builder(getContext())
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        // Delete quiz
                        mGratoViewModel.getResponseDeleteQuiz().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                if(voidResponse.code() == 200){

                                    updateListQuiz();

                                    Toast.makeText(getActivity(), "Delete successful!", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(getActivity(), "Delete fail! Please try again!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        mGratoViewModel.deleteQuiz(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                quiz.getQuiz_name()
                        );
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_delete)
                .show();



    }

    private void updateListQuiz(){
        mGratoViewModel.fetchAllQuizs(
                loginResponseSession.getToken(),
                "CO3005",
                202
        );
    }
}
