package com.example.grato_gv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;

public class MainActivity_welcome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truong_welcome);
        ImageView imageLogo = (ImageView) findViewById(R.id.logo_grato);
        imageLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        
        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = SessionManagement.getInstance(MainActivity_welcome.this);
        String session = sessionManagement.getSession();

        if(session != null){
            //user id logged in and so move to mainActivity
            Intent intent = new Intent(MainActivity_welcome.this, TruongListSubjectActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}