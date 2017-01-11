package com.hitapps.sdk.retrofitInterface;

import com.hitapps.sdk.model.ads.AdsApp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Duy on 1/11/2017.
 */

public interface RetrofitGet {
    @Streaming
    @GET
    Call<AdsApp> getNoti(@Url String url);
}
