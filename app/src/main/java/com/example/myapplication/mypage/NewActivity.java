package com.example.myapplication.mypage;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class NewActivity extends MainActivity{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_button_page);
    }
}
