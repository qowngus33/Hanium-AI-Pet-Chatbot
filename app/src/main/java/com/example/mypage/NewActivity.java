package com.example.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends MainActivity{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        TextView tv = findViewById(R.id.tv);
    }
}
