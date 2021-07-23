package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.ExamAnswersActivity;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class ExamCodeAdapter extends RecyclerView.Adapter<ExamCodeAdapter.ExamCodeViewHolder> {

    Context context;
    ArrayList<ExamCode> lstExamCode;

    ExamCodeItemClickListener mExamCodeItemClickListener;

    public ExamCodeAdapter(ArrayList<ExamCode> lstExamCode) {
        this.lstExamCode = lstExamCode;
    }

    @NonNull
    @Override
    public ExamCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_exam_code_item, parent, false);
        return new ExamCodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamCodeViewHolder holder, int position) {
        ExamCode examCode = lstExamCode.get(position);

        holder.tvExamCodeId.setText(examCode.getExam_code_id());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExamAnswersActivity.class);
                intent.putExtra("examCodeId", examCode.getExam_code_id());
                intent.putExtra("keyAnswer", examCode.getKey_answer());
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExamCodeItemClickListener.onDeleteExamCodeClick(examCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstExamCode == null ? 0 : lstExamCode.size();
    }

    class ExamCodeViewHolder extends RecyclerView.ViewHolder {

        TextView tvExamCodeId;
        ImageView imgDelete;

        public ExamCodeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExamCodeId = itemView.findViewById(R.id.tvExamCodeId);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

    public interface ExamCodeItemClickListener{
        // ExamCodeActivity sẽ định nghĩa
        void onDeleteExamCodeClick(ExamCode examCode);
    }

    public void setmExamCodeClickListener(ExamCodeItemClickListener examCodeClickListener){
        mExamCodeItemClickListener = examCodeClickListener;
    }
}
