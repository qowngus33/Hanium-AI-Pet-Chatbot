package com.example.myapplication.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 * UI에 인증된 사용자 세부 정보를 노출하는 클래스입니다.
 */
class LoggedInUserView {
    private String displayName;
    /*... other data fields that may be accessible to the UI
    UI에 액세스할 수 있는 기타 데이터 필드*/

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}