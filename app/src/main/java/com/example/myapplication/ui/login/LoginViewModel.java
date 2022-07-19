package com.example.myapplication.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.myapplication.data.LoginRepository;
import com.example.myapplication.data.Result;
import com.example.myapplication.data.model.LoggedInUser;
import com.example.myapplication.R;

public class LoginViewModel extends ViewModel {

    //LoginFormState, LoginResult 변경 가능 데이터.
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    //LoginFormState, LoginResult 값 입력받고는 읽기만 가능.
    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }
    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    //로그인
    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        //별도의 비동기 작업에서 시작할 수 있습니다.
        Result<LoggedInUser> result = loginRepository.login(username, password);

        //로그인 데이터가 성공했을 때, loginResult 셋팅
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
            //로그인 데이터가 실패했을 때, loginResult 셋팅
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    //로그인 데이터가 변경됐을 때 메소드.
    public void loginDataChanged(String username, String password) {
        //userName 유효하지 않을 때
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        //pw 유효하지 않을 때
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        //둘 다 유효할 때
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check. A placeholder 사용자 이름 유효성 검사.
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        //userName에 '@'들어가면 이메일 유효성 검사 >> 변경 필요!
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check. A placeholder 비밀번호 유효성 검사.
    //pw가 null 아니고 공백제외 총 길이가 5 이상일때 리턴.
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}