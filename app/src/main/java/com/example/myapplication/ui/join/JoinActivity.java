package com.example.myapplication.ui.join;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private JoinUserState joinUserState = new JoinUserState();
    private AlertDialog dialog;
    private boolean validate = true;
    private boolean isJoinSuccess = false;
    private ServiceAPI service = RetrofitClient.getClient().create(ServiceAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText emailEditText = (EditText) findViewById(R.id.editTextEmail);
        final EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        final EditText passwordReEditText = (EditText) findViewById(R.id.editTextPasswordCheck);
        final Button joinButton = (Button) findViewById(R.id.joinButton);
        final Button emailCheckButton = (Button) findViewById(R.id.emailCheckButton);

        TextWatcher emailAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                joinUserState.setEmail(emailEditText.getText().toString());
                if(!joinUserState.isEmailValid()) {
                    emailEditText.setError("이메일 형식이 잘못되었습니다.");
                }
            }
        };
        TextWatcher passwordAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                joinUserState.setPassword(passwordEditText.getText().toString());
                if(!joinUserState.isPasswordValid()) {
                    passwordEditText.setError("비밀번호는 여섯자리 이상, 영어와 숫자로 구성해주세요.");
                }
            }
        };
        TextWatcher passwordReEnterAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                joinUserState.setRePassword(passwordReEditText.getText().toString());
                if(!joinUserState.isPasswordSame()) {
                    passwordReEditText.setError("비밀번호가 다릅니다.");
                }
            }
        };

        emailEditText.addTextChangedListener(emailAfterTextChangedListener);
        passwordEditText.addTextChangedListener(passwordAfterTextChangedListener);
        passwordReEditText.addTextChangedListener(passwordReEnterAfterTextChangedListener);

        // 이메일 중복 확인
        emailCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!joinUserState.isEmailValid()) {
                    emailEditText.setError("이메일 형식이 잘못되었습니다.");
                    return;
                } else {
                    validateEmail(new EmailValidationData(joinUserState.getEmail()));
                }
            }
        });

        // 회원가입 버튼
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (joinUserState.isValidData() && validate) {
                    startJoin(new JoinData(joinUserState.getEmail(),joinUserState.getPassword()));
                } else if(!validate) {
                    Toast.makeText(getApplicationContext(), "이메일 인증을 완료해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "이메일, 비밀번호 형식을 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void validateEmail(EmailValidationData data) {
        service.emailValidation(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    // finish();
                }
            }
            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), JoinEmailCheckActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }
}

