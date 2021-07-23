package com.example.grato_gv.Repository;

import android.util.Log;

import com.example.grato_gv.Api.ApiRequest;
import com.example.grato_gv.Api.RetrofitInit;
import com.example.grato_gv.Model.InfoClass;
import com.example.grato_gv.Model.Student;

import java.util.List;
import java.util.ResourceBundle;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.Response;

// Thong create

public class ClassRepository {
    private static ClassRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private ClassRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static ClassRepository getInstance(){
        if(mInstance == null){
            mInstance = new ClassRepository();
        }
        return mInstance;
    }

    public Maybe<List<String>> getListClass(String token, String sub_id, Integer semester_id, String user_id){
        Log.d("getListClass", token + " " + sub_id + " " + semester_id + " " + user_id);
        return mApiRequest.getListClass(token,sub_id,semester_id,user_id);
    }

    public Maybe<List<InfoClass>> countStudent(String token, String sub_id, Integer semester_id, String class_id){
        return mApiRequest.countStudent(token,sub_id,semester_id,class_id);
    }

    public Maybe<Response<Void>> createClass(String token, String sub_id, Integer semester_id, String class_id, String room
                                        ,String start_time, String end_time, String user_id){
        return mApiRequest.createClass(token, sub_id, semester_id, class_id, room, start_time, end_time, user_id);
    }

    public Maybe<List<Student>> getStudentInClass(String token, String sub_id, Integer semester_id, String class_id){
        return mApiRequest.getStudentInClass(token,sub_id,semester_id,class_id);
    }

}
