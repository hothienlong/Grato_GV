package com.example.grato_gv.Repository;

import android.util.Log;

import com.example.grato_gv.Api.ApiRequest;
import com.example.grato_gv.Api.RetrofitInit;
import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.Model.Quiz;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class QuizRepository {
    private static QuizRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private QuizRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static QuizRepository getInstance(){
        if(mInstance == null){
            mInstance = new QuizRepository();
        }
        return mInstance;
    }

    public Maybe<List<Quiz>> getAllQuizs(String token, String sub_id, Integer semester_id){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getAllQuizs(token, sub_id, semester_id);
    }

    public Maybe<Quiz> getAQuiz(String token, String sub_id, Integer semester_id, String quiz_name){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getAQuiz(token, sub_id, semester_id, quiz_name);
    }

    public Maybe<Response<Void>> addQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer max_time, Integer no_question, String deadline){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.addQuiz(authorization, sub_id, semester_id, quiz_name, max_time, no_question, deadline);
    }

    public Maybe<Response<Void>> addQuestionToQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String content){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.addQuestionToQuiz(authorization, sub_id, semester_id, quiz_name, question_id, content);
    }

    public Maybe<Response<Void>> addAnswerToQuestion(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String answer_id, Integer right_answer, String content){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.addAnswerToQuestion(authorization, sub_id, semester_id, quiz_name, question_id, answer_id, right_answer, content);
    }

    public Maybe<Response<Void>> modifyQuestionOfQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String content){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.modifyQuestionOfQuiz(authorization, sub_id, semester_id, quiz_name, question_id, content);
    }

    public Maybe<Response<Void>> deleteQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.deleteQuiz(authorization, sub_id, semester_id, quiz_name);
    }

    public Maybe<List<QuestionAnswer>> getAllQuestionsAndAnswersOfQuiz(String token, String sub_id, Integer semester_id, String quiz_name){
        Log.d("quizrepo", sub_id + " " + semester_id);
        return mApiRequest.getAllQuestionsAndAnswersOfQuiz(token, sub_id, semester_id, quiz_name);
    }
}
