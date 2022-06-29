package com.example.mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //설정 목록 생성
        ArrayList<String> listSet = new ArrayList<>();
        listSet.add("내 프로필");
        listSet.add("반려동물 프로필");
        listSet.add("문진표 관리");


        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager); //LayoutManger 설정

        CustomAdapter customAdapter = new CustomAdapter(listSet);
        //==========[Click 이벤트]=========
        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemclickListener() {
            @Override
            public void onItemclicked(int position, String data) {
                Toast.makeText(getApplicationContext(), "Position" + position +", Data" + data, Toast.LENGTH_SHORT).show();
            }
        });
        //====================================

        recyclerView.setAdapter(customAdapter); //어댑터 설정
    }
}