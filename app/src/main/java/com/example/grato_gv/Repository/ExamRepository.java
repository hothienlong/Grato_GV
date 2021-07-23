package com.example.grato_gv.Repository;

import android.util.Log;

import com.example.grato_gv.Api.ApiRequest;
import com.example.grato_gv.Api.RetrofitInit;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.ExamReview;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Response;

public class ExamRepository {
    private static ExamRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ExamRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ExamRepository getInstance(){
        if(mInstance == null){
            mInstance = new ExamRepository();
        }
        return mInstance;
    }

    public Maybe<List<Exam>> getAllExams(String token, String sub_id, Integer semester_id){
        return mApiRequest.getAllExams(token, sub_id, semester_id);
    }

    public Maybe<Response<Void>> addExam(String authorization, String sub_id, Integer semester_id, String exam_name, Integer no_question){
        return mApiRequest.addExam(authorization, sub_id, semester_id, exam_name, no_question);
    }

    public Maybe<Response<Void>> deleteExam(String authorization, String sub_id, Integer semester_id, String exam_name){
        return mApiRequest.deleteExam(authorization, sub_id, semester_id, exam_name);
    }

    public Maybe<List<ExamCode>> getAllExamCodes(String token, String sub_id, Integer semester_id, String exam_name){
        return mApiRequest.getAllExamCodes(token, sub_id, semester_id, exam_name);
    }

    public Maybe<Response<Void>> deleteExamCode(String authorization, String sub_id, Integer semester_id, String exam_name, String exam_code_id){
        return mApiRequest.deleteExamCode(authorization, sub_id, semester_id, exam_name, exam_code_id);
    }

    public Maybe<List<ExamReview>> getAllExamReviews(String token, String sub_id, Integer semester_id, String exam_name){
        Log.d(getClass().getName(), sub_id + semester_id + exam_name);
        return mApiRequest.getAllExamReviews(token, sub_id, semester_id, exam_name);
    }

    public Maybe<Response<Void>> deleteExamReview(String authorization, String sub_id, Integer semester_id, String exam_name, String exam_code_id, String student_id){
        return mApiRequest.deleteExamReview(authorization, sub_id, semester_id, exam_name, exam_code_id, student_id);
    }
}
