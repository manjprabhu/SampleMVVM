package com.example.samplemvvm.data;

import com.example.samplemvvm.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitRequest {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Response> getResponse();

}
