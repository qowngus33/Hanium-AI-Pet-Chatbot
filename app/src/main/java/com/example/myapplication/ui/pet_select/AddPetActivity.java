package com.example.myapplication.ui.pet_select;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.mypage.MainActivity;
import com.example.myapplication.ui.mypage.NewActivity;
import com.example.myapplication.ui.setting.PetProfileActivity;

import java.util.ArrayList;

public class AddPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpetprofile);

        // 이름, 나이 입력 창
        Button saveButton = (Button) findViewById(R.id.btnAddPetSave);
        Button cancelButton = (Button) findViewById(R.id.btnAddPetCancel);
        Button selectCatButton = (Button) findViewById(R.id.selectCat);
        Button selectDogButton = (Button) findViewById(R.id.selectDog);
        ImageView petprofile = (ImageView) findViewById(R.id.pic);

        Button btnName = findViewById(R.id.btnName);
        Button btnAge = findViewById(R.id.btnAge);
        TextView petNickName = findViewById(R.id.petNickname);
        TextView petAge = findViewById(R.id.petAge);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PetSelectActivity.class);
                startActivity(intent);
            }
        });
        //반려동물 이름 변경
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder name = new AlertDialog.Builder(AddPetActivity.this);
                name.setTitle("닉네임 설정");
                name.setMessage("10자 이내로 닉네임을 설정하세요.");

                final EditText NickName = new EditText(AddPetActivity.this);
                name.setView(NickName);
                name.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String result = NickName.getText().toString();
                        petNickName.setText(result);
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show();
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

        //나이 변경
        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder AgePicker = new AlertDialog.Builder(AddPetActivity.this);

                AgePicker.setTitle("나이변경");
                final NumberPicker AP = new NumberPicker(AddPetActivity.this);
                AgePicker.setView(AP);

                AP.setMinValue(0);
                AP.setMaxValue(30);
                AP.setWrapSelectorWheel(false);
                AP.setValue(0);

                AP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    }
                });

                AgePicker.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        petAge.setText(String.valueOf(AP.getValue()));
                        dialog.dismiss();
                    }
                });
                AgePicker.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AgePicker.show();
            }
        });
        // 개/고양이 버튼 클릭 시에 해당 사진으로 이미지뷰 변경
        selectCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petprofile.setImageResource(R.drawable.cat);
            }
        });
        selectDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petprofile.setImageResource(R.drawable.img);
            }
        });
    }
}