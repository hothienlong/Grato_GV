package com.example.grato_gv.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import retrofit2.Response;

public class CreateClassActivity extends AppCompatActivity {

    Button btnCreateClass;
    EditText etSemes, etSub, etClass, etRoom, etST, etET;
    LoginResponse loginResponse;
    GratoViewModel mGratoViewModel;
    String subId, classId, room, startTime, endTime;
    Integer sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        // get token
        SessionManagement sessionManagement = SessionManagement.getInstance(this);

        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponse = gson.fromJson(loginResponseJson, LoginResponse.class);

        addControls();

        btnCreateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }

    private void getData() {
        sem = Integer.parseInt(etSemes.getText().toString());
        subId = etSub.getText().toString();
        classId = etClass.getText().toString();
        room = etRoom.getText().toString();
        startTime = etST.getText().toString();
        endTime = etET.getText().toString();

        Log.d("semester",sem.toString());
        Log.d("subject",subId);
        Log.d("class",classId);
        Log.d("room",room);
        Log.d("start time",startTime);
        Log.d("end time",endTime);

        mGratoViewModel = new ViewModelProvider(this).get(GratoViewModel.class);
        mGratoViewModel.getResponseCreateClass().observe(this, new Observer<Response<Void>>() {
            @Override
            public void onChanged(Response<Void> voidResponse) {
                Log.d("Create class",voidResponse.message());
            }
        });

        mGratoViewModel.createClass(loginResponse.getToken(),subId,sem,classId,room,startTime,endTime,loginResponse.getUser().getId());

    }

    private void addControls() {
        btnCreateClass = findViewById(R.id.button_create_class);
        etSemes = findViewById(R.id.sem_id);
        etClass = findViewById(R.id.class_id);
        etSub = findViewById(R.id.sub_id);
        etRoom = findViewById(R.id.room);
        etST = findViewById(R.id.start_time);
        etET = findViewById(R.id.end_time);
    }
}