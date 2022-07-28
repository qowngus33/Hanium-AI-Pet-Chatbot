package com.example.myapplication.ui.setting;

import com.google.gson.annotations.SerializedName;

//프로필 이메일, pw 정보 요청
public class ProfileRequest {
    @SerializedName("memberEmail")
    public String inputId;

    @SerializedName("memberPassword")
    public String inputPw;

    @SerializedName("petName")
    public String petName;

    @SerializedName("petAge")
    public String petAge;

    public ProfileRequest(String inputId, String inputPw, String petName, String petAge) {
        this.inputId = inputId;
        this.inputPw = inputPw;
        this.petName = petName;
        this.petAge = petAge;

    }
}
