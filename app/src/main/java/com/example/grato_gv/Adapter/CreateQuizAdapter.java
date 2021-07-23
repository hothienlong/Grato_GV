package com.example.grato_gv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.example.grato_gv.Activity.AddQuestionActivity;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.R;
import com.example.grato_gv.SessionManagement;
import com.example.grato_gv.ViewModel.GratoViewModel;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import retrofit2.Response;


///////

public class CreateQuizAdapter extends RecyclerView.Adapter<CreateQuizAdapter.QuizViewHolder> {

    Context context;
    ArrayList<Quiz> lstQuiz;

    LoginResponse loginResponseSession;

    QuizItemClickListener mQuizItemClickListener;

    public CreateQuizAdapter(ArrayList<Quiz> lstQuiz) {
        this.lstQuiz = lstQuiz;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_quiz_item, parent, false);

        // get session token
        SessionManagement sessionManagement = SessionManagement.getInstance(context);
        String loginResponseJson = sessionManagement.getSession();
        Gson gson = new Gson();
        loginResponseSession = gson.fromJson(loginResponseJson, LoginResponse.class);

        return new QuizViewHolder(view);
    }

    // gán dữ liệu
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = lstQuiz.get(position);

        holder.tvQuizName.setText(quiz.getQuiz_name());
        holder.tvNumQuestion.setText(quiz.getNo_question() + " questions");
        holder.tvTime.setText(quiz.getMax_time() + " minutes");

        // Deadline
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = formatter.parse(quiz.getDeadline());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d(getClass().getName(), quiz.getDeadline());

        SimpleDateFormat newFormatter = new SimpleDateFormat("E, dd MMM yyyy, hh:mm a");
        String formattedDateString = newFormatter.format(date);
        holder.tvDeadline.setText("Deadline: " + formattedDateString);


        // View detail a quiz
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddQuestionActivity.class);
                intent.putExtra("quizName", quiz.getQuiz_name());
                context.startActivity(intent);
            }
        });

        // Add quiz to class
        holder.tvAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpClass popUpClass = new PopUpClass();
                popUpClass.showPopupWindow(v);
            }
        });

        // Delete quiz
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuizItemClickListener.onDeleteQuizClick(quiz);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstQuiz == null ? 0 : lstQuiz.size();
    }

    // lưu lại view để khi gọi lại chỉ cần lấy ra => tăng hiệu suất
    class QuizViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuizName, tvNumQuestion, tvTime, tvDeadline;
        TextView tvAddClass, tvEdit, tvDelete;

        // ánh xạ
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuizName = (TextView) itemView.findViewById(R.id.tvQuizName);
            tvNumQuestion = (TextView) itemView.findViewById(R.id.tvNumQuestion);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDeadline = (TextView) itemView.findViewById(R.id.tvDeadline);
            tvAddClass = (TextView) itemView.findViewById(R.id.tvAddClass);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);

            //////

        }
    }

    public interface QuizItemClickListener{
        // CreateQuizFragment sẽ định nghĩa
        void onDeleteQuizClick(Quiz quiz);
    }

    public void setmQuizClickListener(QuizItemClickListener quizClickListener){
        mQuizItemClickListener = quizClickListener;
    }
}



