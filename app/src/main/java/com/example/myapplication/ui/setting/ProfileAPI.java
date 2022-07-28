package com.example.myapplication.ui.setting;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileAPI {
    //@통신 방식("통신 API명")

    //회원 탈퇴
    @DELETE("/Profile/{memberEmail}")
    Call<ProfileResponse> deletePost(@Path("memberEmail")int id);

    //반려동물 정보 수정
    @PUT("/Profile/{petAge}")
    Call<ProfileResponse> updatePetPost(@Path("petAge") String petAge,
                                        @Body ProfileRequest profileRequest);

    //반려동물 삭제
    @DELETE("/Profile/{petName}")
    Call<ProfileResponse> deletePetPost(@Path("petName")int name);
}
