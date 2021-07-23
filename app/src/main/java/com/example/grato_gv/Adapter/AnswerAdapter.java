package com.example.grato_gv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    Context context;
    ArrayList<QuestionAnswer> lstQuestionAnswer;

    public AnswerAdapter(ArrayList<QuestionAnswer> lstQuestionAnswer) {
        this.lstQuestionAnswer = lstQuestionAnswer;
    }

    @NonNull
    @NotNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_quiz_answer_item, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AnswerViewHolder holder, int position) {
        QuestionAnswer questionAnswer = lstQuestionAnswer.get(position);

        holder.edtAnswerContent.setText(questionAnswer.getAnswer_content());

        if(questionAnswer.getRight_answer() == 1){
            holder.imgCheckedAnswerRight.setImageResource(R.drawable.long_ic_checked);
            holder.imgDeleteAnswer.setVisibility(View.GONE);
        }
        else {
            holder.imgCheckedAnswerRight.setImageResource(R.drawable.long_ic_unchecked);
        }

//        lstQuestionAnswer.get(position).setAnswe;

    }

    @Override
    public int getItemCount() {
        return lstQuestionAnswer == null ? 0 : lstQuestionAnswer.size();
    }

    class AnswerViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCheckedAnswerRight, imgDeleteAnswer;
        EditText edtAnswerContent;

        public AnswerViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imgCheckedAnswerRight = (ImageView) itemView.findViewById(R.id.imgCheckedAnswerRight);
            imgDeleteAnswer = (ImageView) itemView.findViewById(R.id.imgDeleteAnswer);
            edtAnswerContent = (EditText) itemView.findViewById(R.id.edtAnswerContent);
        }
    }
}
