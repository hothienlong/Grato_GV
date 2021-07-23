package com.example.grato_gv.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grato_gv.Adapter.truongListSubjectAdapter;
import com.example.grato_gv.Model.truongSubject;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TruongListSubjectActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView listSubjectRecyclerview;
    DrawerLayout drawer;
    NavigationView navigationView;
    FloatingActionButton fabLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truong_list_subjects);
        addControls();


//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);

        addEvents();
    }

    private void addEvents() {

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//                // set item as selected to persist highlight
//                item.setChecked(true);
//                // close drawer when item is tapped
//                drawer.closeDrawers();
//
//                int id = item.getItemId();
//                Toast.makeText(TruongListSubjectActivity.this, "alo", Toast.LENGTH_SHORT).show();
//
//                if (id == R.id.nav_logout) {
//                    // Remove session
//                    SessionManagement sessionManagement = new SessionManagement(TruongListSubjectActivity.this);
//                    sessionManagement.removeSession();
//                    // Move to LoginActivity
//                    Intent intent = new Intent(TruongListSubjectActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                }
//
//        //        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        //        drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });


        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove session
                SessionManagement sessionManagement = SessionManagement.getInstance(TruongListSubjectActivity.this);
                sessionManagement.removeSession();
                // Move to LoginActivity
                Intent intent = new Intent(TruongListSubjectActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        ArrayList<truongSubject> lstSubject = new ArrayList<>();
        lstSubject.add(new truongSubject("Principle of Programing Language", "03"));
        lstSubject.add(new truongSubject("Data Structures and Algorithms", "01"));
        lstSubject.add(new truongSubject("Object Oriented Programing", "02"));
        lstSubject.add(new truongSubject("Network System", "04"));

        // tạo adapter
        truongListSubjectAdapter listSubjectAdapter = new truongListSubjectAdapter(lstSubject);

        // performance
        listSubjectRecyclerview.setHasFixedSize(true);

        // set adapter cho Recycler View
        listSubjectRecyclerview.setAdapter(listSubjectAdapter);
    }

    private void addControls() {
        listSubjectRecyclerview = findViewById(R.id.subjectItemRecyclerview);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fabLogout = findViewById(R.id.fabLogout);
    }


    // xử lí sự kiện khi bấm nút navigation => mở side bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}