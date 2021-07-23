package com.example.grato_gv.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_gv.R;

public class ExamAnswersAdapter extends RecyclerView.Adapter<ExamAnswersAdapter.QuizScanAnswersViewHolder> {

    Context context;
    String lstAnswersRow; // mỗi dòng là 1 kí tự

    public ExamAnswersAdapter(String lstAnswersRow) {
        this.lstAnswersRow = lstAnswersRow;
    }


    @NonNull
    @Override
    public QuizScanAnswersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_exam_answers_row, parent, false);
        return new QuizScanAnswersViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizScanAnswersViewHolder holder, int position) {
        holder.tvNum.setText((position+1) + "");


        switch (lstAnswersRow.charAt(position)){
            case 'A':
                Log.d("BBB", position+1 + " A" + " ? " + lstAnswersRow.charAt(position));
                holder.tvA.setBackgroundResource(R.drawable.long_bg_option_answers_chosen_circle);
                break;
            case 'B':
                Log.d("BBB", position+1 + " B"+ " ? " + lstAnswersRow.charAt(position));
                holder.tvB.setBackgroundResource(R.drawable.long_bg_option_answers_chosen_circle);
                break;
            case 'C':
                Log.d("BBB", position+1 + " C"+ " ? " + lstAnswersRow.charAt(position));
                holder.tvC.setBackgroundResource(R.drawable.long_bg_option_answers_chosen_circle);
                break;
            case 'D':
                Log.d("BBB", position+1 + " D"+ " ? " + lstAnswersRow.charAt(position));
                holder.tvD.setBackgroundResource(R.drawable.long_bg_option_answers_chosen_circle);
                break;
            case 'E':
                Log.d("BBB", position+1 + " E"+ " ? " + lstAnswersRow.charAt(position));
                holder.tvE.setBackgroundResource(R.drawable.long_bg_option_answers_chosen_circle);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return lstAnswersRow == null ? 0 : lstAnswersRow.length();
    }

    // Dùng viewholder tránh việc tạo view mới và findviewbyid quá nhiều -> cải thiện performance
    class QuizScanAnswersViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum, tvA, tvB, tvC, tvD, tvE;

        public QuizScanAnswersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tvNum);
            tvA = itemView.findViewById(R.id.tvAnswerA);
            tvB = itemView.findViewById(R.id.tvAnswerB);
            tvC = itemView.findViewById(R.id.tvAnswerC);
            tvD = itemView.findViewById(R.id.tvAnswerD);
            tvE = itemView.findViewById(R.id.tvAnswerE);
        }
    }
}
