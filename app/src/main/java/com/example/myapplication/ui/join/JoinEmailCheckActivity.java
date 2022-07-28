package com.example.myapplication.ui.join;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;

public class JoinEmailCheckActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_check);

        Button checkButton = (Button) findViewById(R.id.emailCheckButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 이메일에 전송한 코드와 일치하는지 확인하는 코드 작성
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinEmailCheckActivity.this);
                dialog = builder.setMessage("회원가입에 성공하였습니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(JoinEmailCheckActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }
}