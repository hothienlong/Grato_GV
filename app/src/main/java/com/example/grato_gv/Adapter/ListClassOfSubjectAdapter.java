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
import com.example.grato_gv.Activity.ViewScanActivity;
import com.example.grato_gv.Activity.ViewStudentInClass;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class ListClassOfSubjectAdapter extends RecyclerView.Adapter<ListClassOfSubjectAdapter.ClassViewHolder>{

    Context context;
    ArrayList<InfoClass> lstInfoClasses;

    public ListClassOfSubjectAdapter(ArrayList<InfoClass> lstInfoClasses) {
        this.lstInfoClasses = lstInfoClasses;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.thong_class_item, parent, false);
        return new ListClassOfSubjectAdapter.ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClassOfSubjectAdapter.ClassViewHolder holder, int position) {
        InfoClass mclass = lstInfoClasses.get(position);

        holder.vtNameClass.setText(mclass.getClassName());
        holder.vtNowStudent.setText((mclass.getNowStudent())+"");

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewStudentInClass.class);
                intent.putExtra("classID",mclass.getClassName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstInfoClasses == null ? 0 : lstInfoClasses.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {

        public TextView vtNameClass,vtNowStudent,vtMaxStudent;
        Button btnView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            vtNowStudent = (TextView) itemView.findViewById(R.id.now_student);
            vtNameClass = (TextView) itemView.findViewById(R.id.name_class);
            btnView = itemView.findViewById(R.id.buttonViewStudent);
        }
    }
}
