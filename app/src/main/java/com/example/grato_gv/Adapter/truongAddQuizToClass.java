package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Activity.ExamAnswersActivity;
import com.example.grato_gv.Model.AddQuizToClass;
import com.example.grato_gv.R;

import java.util.ArrayList;

public class truongAddQuizToClass extends RecyclerView.Adapter<truongAddQuizToClass.QuizToClassViewHolder> {

    Context context;
    ArrayList<AddQuizToClass> lstQuizToClass;

    public truongAddQuizToClass(ArrayList<AddQuizToClass> lstQuizToClass) {
        this.lstQuizToClass = lstQuizToClass;
    }

    @NonNull
    @Override
    public QuizToClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.truong_layout_item_add_class, parent, false);
        return new QuizToClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizToClassViewHolder holder, int position) {
        AddQuizToClass quizToClass = lstQuizToClass.get(position);

        holder.tvAddQuizToClass.setText(quizToClass.getTxtClassName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExamAnswersActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstQuizToClass == null ? 0 : lstQuizToClass.size();
    }

    class QuizToClassViewHolder extends RecyclerView.ViewHolder {

        TextView tvAddQuizToClass;

        public QuizToClassViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddQuizToClass = itemView.findViewById(R.id.txtClassName);
        }
    }
}
