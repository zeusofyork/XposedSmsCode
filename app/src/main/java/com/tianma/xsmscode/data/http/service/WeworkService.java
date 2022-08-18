package com.tianma.xsmscode.data.http.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Retrofit Service for Wework
 */
public interface WeworkService {

    @POST
    Call<ResponseBody> sendOfWebhook(@Url String url, @Body RequestBody body);

    @POST
    Call<ResponseBody> sendOfApp(@Url String url, @Body RequestBody body);
}
