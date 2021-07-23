package com.example.grato_gv.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.grato_gv.Model.Exam;
import com.example.grato_gv.Model.ExamCode;
import com.example.grato_gv.Model.ExamReview;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.Model.LoginResponse;
import com.example.grato_gv.Model.QuestionAnswer;
import com.example.grato_gv.Model.Quiz;
import com.example.grato_gv.Model.Student;
import com.example.grato_gv.Repository.ClassRepository;
import com.example.grato_gv.Repository.QuizRepository;
import com.example.grato_gv.Repository.ExamRepository;
import com.example.grato_gv.Repository.UserRepository;

import java.lang.reflect.Member;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class GratoViewModel extends ViewModel {

    private UserRepository mUserRepository;
    private MutableLiveData<LoginResponse> mLoginResponse;
    private LoginFailListener loginFailListener;

    private QuizRepository mQuizRepository;
    private MutableLiveData<List<Quiz>> mAllQuizs;
    private MutableLiveData<Quiz> mAQuiz;
    private MutableLiveData<Response<Void>> mAddQuiz;
    private MutableLiveData<Response<Void>> mDeleteQuiz;
    private MutableLiveData<List<QuestionAnswer>> mAllQuestionAnswerQuiz;
    private MutableLiveData<Response<Void>> mAddQuestionToQuiz;
    private MutableLiveData<Response<Void>> mAddAnswerToQuestion;
    private MutableLiveData<Response<Void>> mModifyQuestionOfQuiz;

    private ExamRepository mExamRepository;
    private MutableLiveData<List<Exam>> mAllExams;
    private MutableLiveData<Response<Void>> mAddExam;
    private MutableLiveData<Response<Void>> mDeleteExam;
    private MutableLiveData<List<ExamCode>> mAllExamCodes;
    private MutableLiveData<Response<Void>> mDeleteExamCode;
    private MutableLiveData<List<ExamReview>> mAllExamReviews;
    private MutableLiveData<Response<Void>> mDeleteExamReview;

    private ClassRepository mClassRepository;
    private MutableLiveData<List<String>> mListClass;
    private MutableLiveData<List<InfoClass>> mCountStudent;
    private MutableLiveData<Response<Void>> mCreateClass;
    private MutableLiveData<List<Student>> mListStudentInClass;

    public GratoViewModel() {
        mUserRepository = UserRepository.getInstance();
        mLoginResponse = new MutableLiveData<>();

        mQuizRepository = QuizRepository.getInstance();
        mAllQuizs = new MutableLiveData<>();
        mAQuiz = new MutableLiveData<>();
        mAddQuiz = new MutableLiveData<>();
        mDeleteQuiz = new MutableLiveData<>();
        mAllQuestionAnswerQuiz = new MutableLiveData<>();
        mAddQuestionToQuiz = new MutableLiveData<>();
        mAddAnswerToQuestion = new MutableLiveData<>();
        mModifyQuestionOfQuiz = new MutableLiveData<>();

        mExamRepository = ExamRepository.getInstance();
        mAllExams = new MutableLiveData<>();
        mAddExam = new MutableLiveData<>();
        mDeleteExam = new MutableLiveData<>();
        mAllExamCodes = new MutableLiveData<>();
        mDeleteExamCode = new MutableLiveData<>();
        mAllExamReviews = new MutableLiveData<>();
        mDeleteExamReview = new MutableLiveData<>();

        mClassRepository = ClassRepository.getInstance();
        mListClass = new MutableLiveData<>();
        mCountStudent = new MutableLiveData<>();
        mCreateClass = new MutableLiveData<>();
        mListStudentInClass = new MutableLiveData<>();
    }

    public void login(String id, String password){
        mUserRepository.login(id, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull LoginResponse loginResponse) {
           //             Log.d("Viewmodel", loginResponse.getUser().getName());
                        mLoginResponse.setValue(loginResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
        //                Log.d("Viewmodel", "Error : " + e.getMessage());
                        loginFailListener.onLoginFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<LoginResponse> getLoginResponse(){
        return mLoginResponse;
    }

    public interface LoginFailListener{
        void onLoginFail();
    }

    public void setLoginFailListener(LoginFailListener loginFailListener){
        this.loginFailListener = loginFailListener;
    }


    public void fetchAQuiz(String token, String sub_id, Integer semester_id, String quiz_name){
        mQuizRepository.getAQuiz(token, sub_id, semester_id, quiz_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Quiz>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Quiz quiz) {
                        mAQuiz.setValue(quiz);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Quiz> getResponseAQuiz(){
        return mAQuiz;
    }


    public void fetchAllQuizs(String token, String sub_id, Integer semester_id){
        mQuizRepository.getAllQuizs(token, sub_id, semester_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Quiz>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Quiz> quizzes) {
                        mAllQuizs.setValue(quizzes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Quiz>> getResponseAllQuizs(){
        return mAllQuizs;
    }


    public void addQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer max_time, Integer no_question, String deadline){
        mQuizRepository.addQuiz(authorization, sub_id, semester_id, quiz_name, max_time, no_question, deadline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mAddQuiz.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseAddQuiz(){
        return mAddQuiz;
    }

    public void deleteQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name){
        mQuizRepository.deleteQuiz(authorization, sub_id, semester_id, quiz_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mDeleteQuiz.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseDeleteQuiz(){
        return mDeleteQuiz;
    }


    public void fetchAllQuestionAnswerQuiz(String token, String sub_id, Integer semester_id, String quiz_name){
        mQuizRepository.getAllQuestionsAndAnswersOfQuiz(token, sub_id, semester_id, quiz_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<QuestionAnswer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<QuestionAnswer> questionAnswers) {
                        mAllQuestionAnswerQuiz.setValue(questionAnswers);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<QuestionAnswer>> getResponseAllQuestionAnswerQuiz(){
        return mAllQuestionAnswerQuiz;
    }

    public void addQuestionToQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String content){
        mQuizRepository.addQuestionToQuiz(authorization, sub_id, semester_id, quiz_name, question_id, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mAddQuestionToQuiz.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseAddQuestionToQuiz(){
        return mAddQuestionToQuiz;
    }



    public void addAnswerToQuestion(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String answer_id, Integer right_answer, String content){
        mQuizRepository.addAnswerToQuestion(authorization, sub_id, semester_id, quiz_name, question_id, answer_id, right_answer, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mAddAnswerToQuestion.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseAddAnswerToQuestion(){
        return mAddAnswerToQuestion;
    }


    public void modifyQuestionOfQuiz(String authorization, String sub_id, Integer semester_id, String quiz_name, Integer question_id, String content){
        mQuizRepository.modifyQuestionOfQuiz(authorization, sub_id, semester_id, quiz_name, question_id, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mModifyQuestionOfQuiz.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseModifyQuestionOfQuiz(){
        return mModifyQuestionOfQuiz;
    }


    public void fetchAllExams(String token, String sub_id, Integer semester_id){
        mExamRepository.getAllExams(token, sub_id, semester_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Exam>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Exam> exams) {
                        mAllExams.setValue(exams);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Exam>> getResponseAllExams(){
        return mAllExams;
    }

    public void addExam(String authorization, String sub_id, Integer semester_id, String exam_name, Integer no_question){
        mExamRepository.addExam(authorization, sub_id, semester_id, exam_name, no_question)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mAddExam.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseAddExam(){
        return mAddExam;
    }

    public void deleteExam(String authorization, String sub_id, Integer semester_id, String exam_name){
        mExamRepository.deleteExam(authorization, sub_id, semester_id, exam_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mDeleteExam.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseDeleteExam(){
        return mDeleteExam;
    }

    public void fetchAllExamCodes(String token, String sub_id, Integer semester_id, String exam_name){
        mExamRepository.getAllExamCodes(token, sub_id, semester_id, exam_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ExamCode>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ExamCode> examCodes) {
                        mAllExamCodes.setValue(examCodes);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<ExamCode>> getResponseAllExamCodes(){
        return mAllExamCodes;
    }

    public void deleteExamCode(String authorization, String sub_id, Integer semester_id, String exam_name, String exam_code_id){
        mExamRepository.deleteExamCode(authorization, sub_id, semester_id, exam_name, exam_code_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mDeleteExamCode.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseDeleteExamCode(){
        return mDeleteExamCode;
    }

    public void fetchAllExamReviews(String token, String sub_id, Integer semester_id, String exam_name){
        mExamRepository.getAllExamReviews(token, sub_id, semester_id, exam_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<ExamReview>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ExamReview> examReviews) {
                        mAllExamReviews.setValue(examReviews);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<ExamReview>> getResponseAllExamReviews(){
        return mAllExamReviews;
    }

    public void deleteExamReview(String authorization, String sub_id, Integer semester_id, String exam_name, String exam_code_id, String student_id){
        mExamRepository.deleteExamReview(authorization, sub_id, semester_id, exam_name, exam_code_id, student_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mDeleteExamReview.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Viewmodel", "Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Response<Void>> getResponseDeleteExamReview(){
        return mDeleteExamReview;
    }

    //================Thong do================//
    // list class
    public void fetchListClass(String token, String sub_id, Integer semester_id, String user_id){
        mClassRepository.getListClass(token, sub_id, semester_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<String> strings) {
                        mListClass.setValue(strings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("View model error list class",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<String>> getResponseListClass(){
        return mListClass;
    }

    // count student of a class
    public void fetchCountStudent(String token, String sub_id, Integer semester_id, String class_id){
        mClassRepository.countStudent(token, sub_id, semester_id,class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<InfoClass>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<InfoClass> infoClasses) {
                        mCountStudent.setValue(infoClasses);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<InfoClass>> getResponseCountStudent(){
        return mCountStudent;
    }

    // create class
    public void createClass(String token, String sub_id, Integer semester_id, String class_id, String room
            ,String start_time, String end_time, String user_id){
        mClassRepository.createClass(token, sub_id, semester_id, class_id, room, start_time, end_time, user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        mCreateClass.setValue(voidResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("View model error create class",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<Response<Void>> getResponseCreateClass(){
        return mCreateClass;
    }

    // list student in class
    public void fetchListStudentInClass(String token, String sub_id, Integer semester_id, String class_id){
        mClassRepository.getStudentInClass(token, sub_id, semester_id,class_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Student>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Student> students) {
                        mListStudentInClass.setValue(students);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<Student>> getResponseListStudent(){
        return mListStudentInClass;
    }

    //=======================================//
}
