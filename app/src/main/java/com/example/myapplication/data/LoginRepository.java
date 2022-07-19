package com.example.myapplication.data;

import com.example.myapplication.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 * 원격 데이터 소스에서 인증 및 사용자 정보를 요청하는 클래스와
 * 로그인 상태 및 사용자 자격 증명 정보의 메모리 내 캐시를 유지 관리합니다.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // 사용자 자격 증명이 로컬 저장소에 캐시되는 경우 암호화하는 것이 좋습니다.
    // @see https://developer.android.com/training/articles/keystore

    private LoggedInUser user = null;

    // private constructor : singleton access. private 생성자 : 싱글톤 접근
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    //user가 null이 아니면 isLoggedIn = true
    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // 사용자 자격 증명이 로컬 저장소에 캐시되는 경우 암호화하는 것이 좋습니다.
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // 로그인 처리
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}