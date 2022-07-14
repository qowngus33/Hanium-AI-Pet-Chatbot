package com.example.myapplication.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;

public class ProfileActivity extends SettingActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView ID = findViewById(R.id.userID);
        TextView pwchange = findViewById(R.id.pwchange);
        TextView logout = findViewById(R.id.logout);
        TextView deleteinfo = findViewById(R.id.deleteinfo);

        //비밀번호 변경
        pwchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //비밀번호 재설정 화면으로 넘어가기
                Intent intent = new Intent(getApplicationContext(), New.class);
                startActivity(intent);
            }
        });
        
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 화면으로 돌아가기
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //회원 탈퇴
        deleteinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder name = new AlertDialog.Builder(ProfileActivity.this);
                name.setTitle("회원탈퇴");
                name.setMessage("정말로 탈퇴하시겠습니까?");

                name.setPositiveButton("탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        //회원정보 삭제 후, 초기 로그인 화면으로 돌아가기
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                name.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                name.show();
            }
        });
    }
}
