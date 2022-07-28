package com.example.myapplication.ui.login;
import com.google.gson.annotations.SerializedName;


public class PasswordChangeRequest {
    @SerializedName("memberEmail")
    public String inputId;

    @SerializedName("memberPassword")
    public String inputPw;

    @SerializedName("sendCode")
    public int sendCode;

    @SerializedName("receivedCode")
    public int receivedCode;

    public PasswordChangeRequest(String inputId, String inputPw, int sendCode, int receivedCode) {
        this.inputId = inputId;
        this.inputPw = inputPw;
        this.sendCode = sendCode;
        this.receivedCode = receivedCode;
    }
}
