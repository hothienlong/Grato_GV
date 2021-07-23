package com.example.grato_gv.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grato_gv.Activity.ViewStudentInClass;
import com.example.grato_gv.Adapter.ListClassOfSubjectAdapter;
import com.example.grato_gv.Adapter.ViewStudentInClassAdapter;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Student;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class StudentInClass extends Fragment {

    GratoViewModel mGratoViewModel;
    LoginResponse loginResponse;

    RecyclerView classRecyclerView;
    View view;
    String classID;

    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_in_class, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        classID = getActivity().getIntent().getExtras().getString("classID");
        Log.d("aaaaaaaaaaa",classID);

        addControls();
        getData();
        return view;
    }

    private void getData(){
        ArrayList<Student> lstStudent = new ArrayList<>();
//        lstStudent.add(new Student("Cao Xuan Tai","1811222"));
//        lstStudent.add(new Student("H'Hen Nie","1812353"));
//        lstStudent.add(new Student("Do My Linh","1812332"));
//        lstStudent.add(new Student("Nguyen Xuan Tien","1812340"));

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseListStudent().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                Log.d("list student in class",students.toString());
                ViewStudentInClassAdapter viewStudentInClass = new ViewStudentInClassAdapter((ArrayList<Student>) students);
                classRecyclerView.setHasFixedSize(true);
                classRecyclerView.setAdapter(viewStudentInClass);
            }
        });

        mGratoViewModel.fetchListStudentInClass(loginResponse.getToken(),"CO3005",202,classID);

    }

    private void addControls(){
        fab = view.findViewById(R.id.addStudent);
        classRecyclerView = view.findViewById(R.id.list_student_in_class);
    }
}