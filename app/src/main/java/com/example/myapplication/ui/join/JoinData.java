package com.example.myapplication.ui.join;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("memberEmail")
    private String userEmail;

    @SerializedName("memberPassword")
    private String userPwd;

    public JoinData(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}
