package com.example.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLoginBinding;

public class PasswordChangeActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change);
        final Button button = (Button) findViewById(R.id.checkButton);
        final EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        final EditText passwordEditTextCheck = (EditText) findViewById(R.id.editTextPasswordCheck);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 비밀번호 재설정하는 과정 생략. 여기에 구현해야함. "재설정" 버튼을 클릭 시에 로그인으로 넘어감
                if(passwordEditTextCheck.getText().toString().equals(passwordEditText.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    System.out.println(passwordEditTextCheck.getText()+" "+passwordEditText.getText());
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_LONG).show();
               }

            }
        });
    }


}