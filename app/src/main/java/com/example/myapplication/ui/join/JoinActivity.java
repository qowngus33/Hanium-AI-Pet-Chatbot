package com.example.myapplication.ui.join;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ui.newlogin.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toast.makeText(getApplicationContext(),"welcome!",Toast.LENGTH_SHORT);
        Button joinBtn = (Button) findViewById(R.id.joinButton);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"welcome!",Toast.LENGTH_SHORT);
            }
        });
    }
}