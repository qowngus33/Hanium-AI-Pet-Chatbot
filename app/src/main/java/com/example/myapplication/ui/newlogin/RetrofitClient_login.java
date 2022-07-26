package com.example.myapplication.ui.newlogin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient_login {
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "https://12b969e5-87a6-4fca-a942-178ea253fdbc.mock.pstmn.io";
    private static LoginAPI LoginAPI;
    private static Retrofit instance = null;

    private RetrofitClient_login() {
    }

    public static Retrofit getInstance() {
        //retrofit 설정
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginAPI = instance.create(LoginAPI.class);
        }
        return instance;
    }
    public static LoginAPI getRetrofitInterface() {
        return LoginAPI;
    }

}
