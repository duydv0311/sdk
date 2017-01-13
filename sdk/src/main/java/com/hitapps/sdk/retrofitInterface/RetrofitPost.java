package com.hitapps.sdk.retrofitInterface;

import com.hitapps.sdk.model.UserInfor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Duy on 1/11/2017.
 */

public interface RetrofitPost {
    @POST("api/apps/install/")
    Call<UserInfor> postUserInfor(@Body UserInfor userInfor);
}
