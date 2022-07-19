package com.example.myapplication.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 * 인증 결과 : 성공(사용자 세부 정보) 또는 오류 메시지.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}