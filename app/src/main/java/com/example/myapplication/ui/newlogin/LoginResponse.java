package com.example.myapplication.ui.newlogin;

import com.google.gson.annotations.SerializedName;

//서버에서 받은 결과
public class LoginResponse {
    @SerializedName("statusCode")
    public int statusCode;

    @SerializedName("responseMessage")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}
