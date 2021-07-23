package com.example.grato_gv.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Adapter.ListClassOfSubjectAdapter;
import com.example.grato_gv.Model.InfoClass;

import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment {

    RecyclerView classRecyclerView;
    View view;
    GratoViewModel mGratoViewModel;
    LoginResponse loginResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, container, false);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(getContext());

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();
        getData();
        return view;
    }

    private void getData(){
        ArrayList<InfoClass> lstInfoClasses = new ArrayList<>();
        lstInfoClasses.add(new InfoClass("L01",50));
        lstInfoClasses.add(new InfoClass("L02",40));
        lstInfoClasses.add(new InfoClass("L03",60));
        lstInfoClasses.add(new InfoClass("L04",10));

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);

                mGratoViewModel.getResponseListClass().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> strings) {
                        Log.d("List class", strings.toString());
                        processData(strings);
                    }
                });

//        mGratoViewModel.fetchListClass(loginResponse.getToken(),"CO3005",202,loginResponse.getUser().getId());

    }

    private void processData(List<String> strings) {
        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseCountStudent().observe(getViewLifecycleOwner(), new Observer<List<InfoClass>>() {
            @Override
            public void onChanged(List<InfoClass> infoClasses) {
                Log.d("count student",infoClasses.toString());
                ListClassOfSubjectAdapter listClassOfSubjectAdapter = new ListClassOfSubjectAdapter((ArrayList<InfoClass>) infoClasses);
                classRecyclerView.setHasFixedSize(true);
                classRecyclerView.setAdapter(listClassOfSubjectAdapter);
            }
        });

        for (String i : strings) {
            Log.d("i",i);
            mGratoViewModel.fetchCountStudent(loginResponse.getToken(), "CO3005", 202, i);
        }
    }

    private void addControls(){
        classRecyclerView = view.findViewById(R.id.class_of_object_recycleview);
    }
}
