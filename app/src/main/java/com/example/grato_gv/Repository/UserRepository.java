package com.example.grato_gv.Repository;

import com.example.grato_gv.Api.ApiRequest;
import com.example.grato_gv.Api.RetrofitInit;
import com.example.grato_gv.Model.LoginResponse;

import io.reactivex.rxjava3.core.Maybe;

public class UserRepository {
    private static UserRepository mInstance = null;
    private ApiRequest mApiRequest = null;

    // khởi tạo ApiRequest
    private UserRepository(){
        mApiRequest = RetrofitInit.getInstance();
    }

    // khởi tạo mInstance
    public static UserRepository getInstance(){
        if(mInstance == null){
            mInstance = new UserRepository();
        }
        return mInstance;
    }

    public Maybe<LoginResponse> login(String id, String password){
        return mApiRequest.login(id, password, "GV");
    }

}
