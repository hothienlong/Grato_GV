package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.ExamCodeActivity;
import com.example.grato_gv.Activity.ViewStudentInClass;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.Model.Student;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class ViewStudentInClassAdapter extends RecyclerView.Adapter<ViewStudentInClassAdapter.StudentViewHolder>{

    Context context;
    ArrayList<Student> lstStudent;

    public ViewStudentInClassAdapter(ArrayList<Student> lstStudent) {
        this.lstStudent = lstStudent;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.thong_member_item, parent, false);
        return new ViewStudentInClassAdapter.StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = lstStudent.get(position);

        holder.vtName.setText(student.getName());
        holder.vtID.setText(student.getId());
        //holder.vtAttendance.setText("Attendance: 7/7");

    }

    @Override
    public int getItemCount() {
        return lstStudent == null ? 0 : lstStudent.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView vtName,vtID,vtAttendance;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            vtName = (TextView) itemView.findViewById(R.id.name_student);
            vtID = (TextView) itemView.findViewById(R.id.id_student);
          //  vtAttendance = (TextView) itemView.findViewById(R.id.attendance);
        }
    }
}
