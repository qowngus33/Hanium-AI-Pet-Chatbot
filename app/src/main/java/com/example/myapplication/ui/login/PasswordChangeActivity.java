package com.example.myapplication.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.myapplication.ui.join.JoinEmailCheckActivity;
import com.example.myapplication.ui.join.JoinUserState;
import com.example.myapplication.ui.join.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordChangeActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private JoinUserState joinUserState = new JoinUserState();
    private LoginAPI service = RetrofitClient.getClient().create(LoginAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change);

        final EditText emailEditText = (EditText) findViewById(R.id.editTextEmailAddress);
        final Button emailCheckButton = (Button) findViewById(R.id.emailCheckButton);
        final Button checkButton = (Button) findViewById(R.id.checkButton);
        final EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        final EditText passwordEditTextCheck = (EditText) findViewById(R.id.editTextPasswordCheck);

        // 본인확인 버튼 구현
        emailCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 이메일로 본인확인하는 것 구현
                // TODO: 다이얼로그로 코드를 입력할 수 있도록 하고, 사용자로부터 받은 입력코드를 서버에 전달하도록 함
                String email = emailEditText.getText().toString();
                joinUserState.setEmail(email);
                // startJoin(new PasswordChangeRequest(joinUserState.getEmail(),joinUserState.getPassword()));
                if (joinUserState.isEmailValid() && true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PasswordChangeActivity.this);
                    dialog = builder.setMessage("이메일이 인증되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create();
                    dialog.show();
                } else {
                    emailEditText.setError("이메일을 확인해주세요.");
                }
            }
        });

        // 재설정 버튼 구현
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 비밀번호 재설정하는 과정 생략. 여기에 구현해야함. "재설정" 버튼을 클릭 시에 로그인으로 넘어감
                joinUserState.setPassword(passwordEditText.getText().toString());
                joinUserState.setRePassword(passwordEditTextCheck.getText().toString());
                if (joinUserState.isValidData()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PasswordChangeActivity.this);
                    dialog = builder.setMessage("비밀번호가 재설정되었습니다.")
                            .setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .create();
                    dialog.show();
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 사용자 입력에 따라 텍스트 알림창을 설정
        TextWatcher passwordAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                joinUserState.setPassword(passwordEditText.getText().toString());
                if (!joinUserState.isPasswordValid()) {
                    passwordEditText.setError("비밀번호는 여섯자리 이상, 영어와 숫자로 구성해주세요.");
                }
            }
        };
        TextWatcher passwordReEnterAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                joinUserState.setRePassword(passwordEditTextCheck.getText().toString());
                if (!joinUserState.isPasswordSame()) {
                    passwordEditTextCheck.setError("비밀번호가 다릅니다.");
                }
            }
        };
        passwordEditText.addTextChangedListener(passwordAfterTextChangedListener);
        passwordEditTextCheck.addTextChangedListener(passwordReEnterAfterTextChangedListener);
    }

    private void startJoin(PasswordChangeRequest data) {
        service.getPasswordChangeResponse(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(PasswordChangeActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.getStatusCode() == 200) {
                    Intent intent = new Intent(getApplicationContext(), JoinEmailCheckActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(PasswordChangeActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace(); // 에러 발생시 에러 발생 원인 단계별로 출력해줌
            }
        });
    }

}