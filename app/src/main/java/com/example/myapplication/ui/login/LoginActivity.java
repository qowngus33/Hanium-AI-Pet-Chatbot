package com.example.myapplication.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.mypage.MainActivity;
import com.example.myapplication.ui.mypage.MainActivity;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.ui.pet_select.PetSelectActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater()); //activity_login 연결
        setContentView(binding.getRoot());

        //loginViewModel 생성
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button signupButton = binding.signup;
        final Button passwordForgetButton = binding.button;
        final ProgressBar loadingProgressBar = binding.loading;

        //로그인폼 상태
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                //로그인폼 null 아니면 유효 데이터로 설정, 로그인 버튼 활성화.
                loginButton.setEnabled(loginFormState.isDataValid());
                //로그인폼, userName 에러나면 Error 메세지 출력.
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                //로그인폼, pw 에러나면 Error 메세지 출력.
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        //로그인 결과
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                //로그인 결과가 null 아니면 로딩바 출력 안 함.
                loadingProgressBar.setVisibility(View.GONE);
                //로그인 결과가 에러면, Error 메세지 출력. showLoginFailed 밑에 함수.
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                //로그인 결과가 성공이면, Success 메세지 출력. updateUiWithUser 밑에 함수.
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //성공하면 로그인 활동 완료 및 파기
                finish();
            }
        });

        //텍스트 입력하는대로 보여주는 메소드.
        TextWatcher afterTextChangedListener = new TextWatcher() {
            //텍스트 입력 전. start = 시작 인덱스, count = 입력완료 글자 수, after = 입력 중인 글자 수
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }
            //텍스트 바뀌는 순간의 메소드.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }
            //텍스트 바뀌고 난 후의 메소드.
            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        //userName, pw 입력창에 바로 보여주는 텍스트 리스너 적용.
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        //pw 입력창, 핸드폰 자체 키보드에 '확인' 누르면 입력한 값 받아오기.
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        //로그인 버튼 클릭시 리스너.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로딩바 보이기
                loadingProgressBar.setVisibility(View.VISIBLE);
                //userName, pw 입력값 가져오기
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                //PetSelect로 화면 전환. >> 이메일 입력시 전환하도록 제한 필요.
                Intent intent = new Intent(getApplicationContext(), PetSelectActivity.class);
                startActivity(intent);
            }
        });
        //가입 버튼 클릭시 리스너.
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        //pw잊었다는 버튼 클릭 리스너.
        passwordForgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PasswordChangeActivity.class);
                startActivity(intent);
            }
        });
    }

    //LoggedInUserView 모델 성공했을 때, 'welcome!'문자와 함께 displayName 출력.
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
    }

    //실패했을 때, 에러 토스트 메세지 출력.
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}