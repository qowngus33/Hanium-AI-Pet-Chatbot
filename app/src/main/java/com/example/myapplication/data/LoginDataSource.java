package com.example.myapplication.data;

import com.example.myapplication.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 로그인 자격 증명으로 인증을 처리하고 사용자 정보를 검색하는 클래스입니다. */

public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication 로그인 사용자 인증 처리
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            //UUID.randomUUID() 고유값이 저장된 인스턴스를 반환하는 메소드 >> 임시 비번 발급에 사용.
                            java.util.UUID.randomUUID().toString().replace("-", ""),
                            "환영합니다.");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication 인증 취소
    }
}