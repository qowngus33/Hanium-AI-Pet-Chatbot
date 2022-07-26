package com.example.myapplication.ui.newlogin;

import com.google.gson.annotations.SerializedName;

//서버에서 받은 결과
public class LoginResponse {
    @SerializedName("stautsCode")
    public String stautsCode;

    @SerializedName("responseMessage")
    private String message;

    @SerializedName("data")
    private String data;

    public String getStatusCode() {
        return stautsCode;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

}
