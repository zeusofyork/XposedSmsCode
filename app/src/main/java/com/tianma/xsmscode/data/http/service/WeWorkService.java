package com.tianma.xsmscode.data.http.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Retrofit Service for WeWork
 */
public interface WeWorkService {

    @POST
    Call<ResponseBody> send(@Url String url, @Body RequestBody body);
}
