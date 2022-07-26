package com.example.myapplication.ui.join;

import javax.xml.transform.Result;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ServiceAPI {
    @POST("/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/validateDuplicateEmail")
    Call<JoinResponse> emailValidation(@Body EmailValidationData data);
}
