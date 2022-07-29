package com.example.myapplication.ui.login;

import com.example.myapplication.ui.join.JoinUserState;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface LoginAPI {
    //@통신 방식("통신 API명")
    @POST("/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
}
