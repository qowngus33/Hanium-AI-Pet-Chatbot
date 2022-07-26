package com.example.myapplication.ui.newlogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.join.JoinActivity;
import com.example.myapplication.ui.pet_select.PetSelectActivity;
//import com.example.myapplication.ui.newlogin.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_password;
    private Button login_button, join_button, pw_change;
    private LoginFormState LoginFormState = new LoginFormState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        login_email = findViewById( R.id.username );
        login_password = findViewById( R.id.password );

        join_button = findViewById( R.id.signup );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, JoinActivity.class );
                startActivity( intent );
            }
        });
        //비밀번호 잊어버렸습니까? 버튼 눌렀을 때
        pw_change = findViewById(R.id.pwbtn);
        pw_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, PasswordChangeActivity.class );
                startActivity( intent );
            }
        });

        //이메일, pw 입력창 액션 리스너
        TextWatcher emailAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                LoginFormState.setEmail(login_email.getText().toString());
                if(!LoginFormState.isEmailValid()) {
                    login_email.setError("이메일 형식이 잘못되었습니다.");
                }
                //data 형식이 유효하면 로그인 버튼 활성화
                login_button.setEnabled(LoginFormState.isValidData());
            }
        };
        TextWatcher passwordAfterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                LoginFormState.setPassword(login_password.getText().toString());
                if(!LoginFormState.isPasswordValid()) {
                    login_password.setError("6자리 이상 입력해주세요.");
                }
                //data 형식이 유효하면 로그인 버튼 활성화
                login_button.setEnabled(LoginFormState.isValidData());
            }
        };

        login_email.addTextChangedListener(emailAfterTextChangedListener);
        login_password.addTextChangedListener(passwordAfterTextChangedListener);

        //pw 입력창, 핸드폰 자체 키보드에 '확인' 누르면 입력한 값 받아오기.
        login_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login_email.getText().toString();
                    login_password.getText().toString();
                }
                return false;
            }
        });

        login_button = findViewById( R.id.login );
        login_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserEmail = login_email.getText().toString();
                String UserPwd = login_password.getText().toString();

                //임시로 화면 연결되게 해놓음.
                Intent intent = new Intent( LoginActivity.this, PetSelectActivity.class );
                startActivity( intent );


                /*서버 연결 후, 성공여부 확인해야하는 부분!
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//로그인 성공시

                                String UserEmail = jsonObject.getString( "UserEmail" );
                                String UserPwd = jsonObject.getString( "UserPwd" );

                                Toast.makeText( getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( LoginActivity.this, MainActivity.class );

                                intent.putExtra( "UserEmail", UserEmail );
                                intent.putExtra( "UserPwd", UserPwd );

                                startActivity( intent );

                            } else {//로그인 실패시
                                Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest( UserEmail, UserPwd, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add( loginRequest );*/

            }
        });
    }
}
