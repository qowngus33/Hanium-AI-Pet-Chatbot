package com.example.myapplication.ui.petSelect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.ui.mainPage.MainActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.util.ArrayList;

public class PetSelectActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_activity_main);

        firstInit();

        addItem("cat", "밤이", "멍멍이");
        addItem("dog", "보리", "냐옹이");
        addItem("dog", "멍이", "냐옹이");
        addItem("cat", "나비", "냐옹이");
        addItem("cat", "냥냥", "냐옹이");

        mRecyclerViewAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "안녕!" + mList.get(pos).getMainText(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerViewAdapter.setOnLongItemClickListener(new RecyclerViewAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(int pos) {
                Toast.makeText(getApplicationContext(), "onLongItemClick position : " + pos, Toast.LENGTH_SHORT).show();
            }
        });
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AddPetActivity.class);
                startActivity(intent);
            }
        });
    }

    public void firstInit(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
    }

    public void addItem(String imgName, String mainText, String subText){
        RecyclerViewItem item = new RecyclerViewItem();

        item.setImgName(imgName);
        item.setMainText(mainText);
        item.setSubText(subText);

        mList.add(item);
    }
}