package com.example.myapplication.ui.newlogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface LoginAPI {
    //@통신 방식("통신 API명")
    @POST("/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("/enterEmailCode/changePw")
    Call<LoginResponse> getPasswordChangeResponse(@Body PasswordChangeRequest passwordChangeRequest);
}
