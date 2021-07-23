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

import com.example.grato_gv.Activity.ViewScanActivity;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.ExamReview;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class ReviewExamAdapter extends RecyclerView.Adapter<ReviewExamAdapter.ReviewQuizScanViewHolder> {

    Context context;
    ArrayList<ExamReview> lstExamReview;

    ExamReviewItemClickListener mExamReviewItemClickListener;

    public ReviewExamAdapter(ArrayList<ExamReview> lstExamReview) {
        this.lstExamReview = lstExamReview;
    }

    @NonNull
    @Override
    public ReviewQuizScanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_review_exam_item, parent, false);
        return new ReviewQuizScanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewQuizScanViewHolder holder, int position) {
        ExamReview examReview = lstExamReview.get(position);

        holder.tvStudentId.setText(examReview.getStudent_id());
        holder.tvStudentName.setText(examReview.getStudent_name());
        holder.tvExamCodeId.setText("Exam code: " + examReview.getExam_code_id());
        holder.tvScore.setText(examReview.getScore() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewScanActivity.class);
                context.startActivity(intent);
            }
        });

        holder.imgDeleteExamReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExamReviewItemClickListener.onDeleteExamReviewClick(examReview);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstExamReview == null ? 0 : lstExamReview.size();
    }

    class ReviewQuizScanViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentId, tvStudentName, tvExamCodeId, tvScore;
        ImageView imgDeleteExamReview;

        public ReviewQuizScanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentId = (TextView) itemView.findViewById(R.id.tvStudentId);
            tvStudentName = (TextView) itemView.findViewById(R.id.tvStudentName);
            tvExamCodeId = (TextView) itemView.findViewById(R.id.tvExamCodeId);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
            imgDeleteExamReview = itemView.findViewById(R.id.imgDeleteExamReview);
        }
    }

    public interface ExamReviewItemClickListener{
        // ReviewExamActivity sẽ định nghĩa
        void onDeleteExamReviewClick(ExamReview examReview);
    }

    public void setmExamReviewClickListener(ExamReviewItemClickListener examReviewClickListener){
        mExamReviewItemClickListener = examReviewClickListener;
    }
}
