package com.example.grato_gv.Api;

import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.ExamReview;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.Student;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {

    // ------------------------- Local ---------------------------
    // user
    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("authenticate/signin")
    Maybe<LoginResponse> login(
            @Field("id") String id,
            @Field("password") String password,
            @Field("job_type") String job_type
    );

    // Quiz
    @GET("teacher/quiz/getQuiz")
    Maybe<Quiz> getAQuiz(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("quiz_name") String quiz_name
    );

    @GET("teacher/quiz/listQuiz")
    Maybe<List<Quiz>> getAllQuizs(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id
    );

    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("teacher/quiz/add_quiz")
    Maybe<Response<Void>> addQuiz(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("quiz_name") String quiz_name,
            @Field("max_time") Integer max_time,
            @Field("no_question") Integer no_question,
            @Field("deadline") String deadline
    );

    @DELETE("teacher/quiz/delete_quiz")
    Maybe<Response<Void>> deleteQuiz(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("quiz_name") String quiz_name
    );

    @GET("teacher/quiz/get_all_questions_and_answers_of_quiz")
    Maybe<List<QuestionAnswer>> getAllQuestionsAndAnswersOfQuiz(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("quiz_name") String quiz_name
    );


    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("teacher/quiz/add_question_to_quiz_proc")
    Maybe<Response<Void>> addQuestionToQuiz(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("quiz_name") String quiz_name,
            @Field("question_id") Integer question_id,
            @Field("content") String content
    );

    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("teacher/quiz/add_answer_to_question_proc")
    Maybe<Response<Void>> addAnswerToQuestion(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("quiz_name") String quiz_name,
            @Field("question_id") Integer question_id,
            @Field("answer_id") String answer_id,
            @Field("right_answer") Integer right_answer,
            @Field("content") String content
    );

    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("teacher/quiz/modify_question_of_quiz")
    Maybe<Response<Void>> modifyQuestionOfQuiz(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("quiz_name") String quiz_name,
            @Field("question_id") Integer question_id,
            @Field("content") String content
    );

    // Exam
    @GET("teacher/exam/viewListExam")
    Maybe<List<Exam>> getAllExams(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id
    );

    @FormUrlEncoded // parse sang dạng form để gửi lên
    @POST("teacher/exam/createNewExam")
    Maybe<Response<Void>> addExam(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("exam_name") String exam_name,
            @Field("no_question") Integer no_question
    );

    @DELETE("teacher/exam/deleteExam")
    Maybe<Response<Void>> deleteExam(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("exam_name") String exam_name
    );

    @GET("teacher/exam/viewListExamCode")
    Maybe<List<ExamCode>> getAllExamCodes(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("exam_name") String exam_name
    );

    @DELETE("teacher/exam/deleteExamCode")
    Maybe<Response<Void>> deleteExamCode(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("exam_name") String exam_name,
            @Query("exam_code_id") String exam_code_id
    );

    @GET("teacher/exam/viewListStudentDoExamCode")
    Maybe<List<ExamReview>> getAllExamReviews(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("exam_name") String exam_name
    );

    @DELETE("teacher/exam/deleteStudentDoExamCode")
    Maybe<Response<Void>> deleteExamReview(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("exam_name") String exam_name,
            @Query("exam_code_id") String exam_code_id,
            @Query("student_id") String student_id
    );

    // ================= Thong do ====================//
    @GET("teacher/class/listclass")
    Maybe<List<String>> getListClass(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("user_id") String user_id
    );
    @GET("teacher/class/countstudent")
    Maybe<List<InfoClass>> countStudent(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );
    @FormUrlEncoded
    @POST("teacher/class/createClass_GV")
    Maybe<Response<Void>> createClass(
            @Header("authorization") String authorization,
            @Field("sub_id") String sub_id,
            @Field("semester_id") Integer semester_id,
            @Field("class_id") String class_id,
            @Field("room") String room,
            @Field("start_time") String start_time,
            @Field("end_time") String end_time,
            @Field("user_id") String user_id
    );
    @GET("teacher/class/liststudent")
    Maybe<List<Student>> getStudentInClass(
            @Header("authorization") String authorization,
            @Query("sub_id") String sub_id,
            @Query("semester_id") Integer semester_id,
            @Query("class_id") String class_id
    );

    //===============================================//
}
