package com.example.myapplication.ui.newlogin;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//회원가입 정보 서버 가져오기
public class RegisterRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://localhost:8080/join";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterRequest(String UserEmail, String UserPwd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("UserEmail", UserEmail);
        map.put("UserPwd", UserPwd);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}