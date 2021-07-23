package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.ExamOptionActivity;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class ListExamAdapter extends RecyclerView.Adapter<ListExamAdapter.ExamViewHolder> {

    Context context;
    ArrayList<Exam> lstExam;

    ExamItemClickListener mExamItemClickListener;

    public ListExamAdapter(ArrayList<Exam> lstExam) {
        this.lstExam = lstExam;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_exam_item, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = lstExam.get(position);

        holder.tvExamName.setText(exam.getExam_name());
        holder.tvNumQuestion.setText(exam.getNo_question().toString());

        // View exam
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExamOptionActivity.class);
                intent.putExtra("examName", exam.getExam_name());
                context.startActivity(intent);
            }
        });

        // Delete exam
        holder.btnDeleteExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExamItemClickListener.onDeleteExamClick(exam);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstExam == null ? 0 : lstExam.size();
    }

    class ExamViewHolder extends RecyclerView.ViewHolder{
        TextView tvExamName, tvNumQuestion;
        ImageView btnDeleteExam;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExamName = itemView.findViewById(R.id.tvExamName);
            tvNumQuestion = itemView.findViewById(R.id.tvNumQuestion);
            btnDeleteExam = itemView.findViewById(R.id.btnDeleteExam);
        }
    }

    // thằng khai báo interface là thằng muốn sử dụng => thằng có thông tin
    public interface ExamItemClickListener{
        // ExamFragment sẽ định nghĩa
        void onDeleteExamClick(Exam exam);
    }

    public void setmExamClickListener(ExamItemClickListener examClickListener){
        mExamItemClickListener = examClickListener;
    }
}
