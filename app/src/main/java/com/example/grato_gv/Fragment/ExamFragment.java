package com.example.grato_gv.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Adapter.ListExamAdapter;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class ExamFragment extends Fragment implements ListExamAdapter.ExamItemClickListener {

    View view;
    RecyclerView examItemRecyclerview;

    GratoViewModel mGratoViewModel;

    LoginResponse loginResponseSession;

    FloatingActionButton fabAdd;

    ListExamAdapter.ExamItemClickListener mExamItemClickListener = this;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exam, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        addEvents();

        return view;
    }

    private void addEvents() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popUpView = getLayoutInflater().inflate(R.layout.layout_popup_add_new_exam, null);

                AlertDialog alertDialog =
                        new AlertDialog.Builder(getContext())
                                .setView(popUpView)
                                .setCancelable(false)
                                .create();
                alertDialog.show();

                // Spinner number question
                Spinner spinnerNumberQuestion = popUpView.findViewById(R.id.spinnerNumberQuestion);
                ArrayAdapter<String> adapterNumberQuestion = new ArrayAdapter<>(
                        getContext(),
                        android.R.layout.simple_spinner_item
                );
                adapterNumberQuestion.add("10 questions");
                adapterNumberQuestion.add("15 questions");
                adapterNumberQuestion.add("20 questions");
                adapterNumberQuestion.add("25 questions");

                adapterNumberQuestion.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinnerNumberQuestion.setAdapter(adapterNumberQuestion);


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
                        EditText edtExamName = popUpView.findViewById(R.id.edtExamName);

                        String numQuestionStr = spinnerNumberQuestion.getSelectedItem().toString();
                        Integer numQuestion= Integer.parseInt(numQuestionStr.split("\\s")[0]);//splits the string based on whitespace


                        // Add exam
                        mGratoViewModel.getResponseAddExam().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                if(voidResponse.code() == 200) {
                                    updateListExams();
                                    Toast.makeText(getActivity(), "Add exam successful!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Add exam fail! Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        mGratoViewModel.addExam(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                edtExamName.getText().toString(),
                                numQuestion
                        );

                        alertDialog.dismiss();
                    }

                });
            }
        });
    }

    private void addControls() {
        examItemRecyclerview = view.findViewById(R.id.examItemRecyclerview);
        fabAdd = view.findViewById(R.id.fabAdd);
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
//        ArrayList<Exam> lstQuizScan = new ArrayList<>();
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));
//        lstQuizScan.add(new Exam("Midterm exam", 20));
//        lstQuizScan.add(new Exam("Final exam", 40));

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

        mGratoViewModel.getResponseAllExams().observe(getViewLifecycleOwner(), new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                // tạo adapter
                ListExamAdapter listExamAdapter = new ListExamAdapter((ArrayList<Exam>) exams);
                listExamAdapter.setmExamClickListener(mExamItemClickListener);

                // performance
                examItemRecyclerview.setHasFixedSize(true);

                // set adapter cho Recycler View
                examItemRecyclerview.setAdapter(listExamAdapter);
            }
        });

        mGratoViewModel.fetchAllExams(loginResponseSession.getToken(), "CO3005", 202);


    }

    private void updateListExams(){
        mGratoViewModel.fetchAllExams(
                loginResponseSession.getToken(),
                "CO3005",
                202
        );
    }

    @Override
    public void onDeleteExamClick(Exam exam) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        // Delete exam
                        mGratoViewModel.getResponseDeleteExam().observe(getViewLifecycleOwner(), new Observer<Response<Void>>() {
                            @Override
                            public void onChanged(Response<Void> voidResponse) {
                                if(voidResponse.code() == 200){

                                    updateListExams();

                                    Toast.makeText(getActivity(), "Delete successful!", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(getActivity(), "Delete fail! Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        mGratoViewModel.deleteExam(
                                loginResponseSession.getToken(),
                                "CO3005",
                                202,
                                exam.getExam_name()
                        );

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_delete)
                .show();

    }
}
