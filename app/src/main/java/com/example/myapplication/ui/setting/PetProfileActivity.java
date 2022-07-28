package com.example.myapplication.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.join.RetrofitClient;
import com.example.myapplication.ui.pet_select.PetSelectActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetProfileActivity extends SettingActivity {
    private TextView petNickName, petAge;
    private Button btnName, btnAge, btnSave, btnDelete;

    private Intent intent;
    private ProfileAPI profileAPI = RetrofitClient.getClient().create(ProfileAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petprofile);

        petNickName = findViewById(R.id.petNickname);
        petAge = findViewById(R.id.petAge);
        btnName = findViewById(R.id.btnName);
        btnAge = findViewById(R.id.btnAge);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        //반려동물 이름 변경
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder name = new AlertDialog.Builder(PetProfileActivity.this);
                name.setTitle("이름 설정");
                name.setMessage("10자 이내로 이름을 설정하세요.");

                final EditText NickName = new EditText(PetProfileActivity.this);
                name.setView(NickName);
                name.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Editable result = NickName.getText();
                        if (result.length()<10) {
                            petNickName.setText(result.toString());
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "이름이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "10자 이내로 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
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
                AlertDialog.Builder AgePicker = new AlertDialog.Builder(PetProfileActivity.this);

                AgePicker.setTitle("나이변경");
                final NumberPicker AP = new NumberPicker(PetProfileActivity.this);
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

        //저장 버튼
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //바뀐 정보 모두 DB로 이동
                updatePetPost();
            }
        });

        //삭제 버튼
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder name = new AlertDialog.Builder(PetProfileActivity.this);
                name.setTitle("반려동물 삭제");
                name.setMessage("정말로 삭제하시겠습니까?");

                name.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        PetProfileDelete();
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

    //반려동물 정보 수정
    public void updatePetPost(){
        String Name = petNickName.getText().toString().trim();
        String Age = petAge.getText().toString().trim();
        ProfileRequest profileRequest = new ProfileRequest(null, null, Name, Age);

        Call<ProfileResponse> call = profileAPI.updatePetPost(Age, profileRequest);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (!response.equals(200)) {
                    Toast.makeText(getApplicationContext(),"변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PetProfileActivity.this);
                builder.setTitle("알림")
                        .setMessage("잠시 후에 다시 시도해주세요.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    //반려동물 정보 삭제
    private void PetProfileDelete() {
        Call<ProfileResponse> call = profileAPI.deletePetPost(10); //이게 무슨 의미인지 잘 모르겠음. 그러나 작동은 됨.
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (!response.equals(200)) {
                    Intent intent = new Intent(PetProfileActivity.this, PetSelectActivity.class);
                    startActivity(intent);
                    PetProfileActivity.this.finish();
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PetProfileActivity.this);
                builder.setTitle("알림")
                        .setMessage("잠시 후에 다시 시도해주세요.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }
}
