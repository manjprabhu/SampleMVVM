package com.example.samplemvvm.data;

import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequest {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Response> getResponse();

//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Call<Response> getPagedResponse();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Response> getPagedResponse(@Query("page") long page,
                         @Query("pageSize") int pageSize);

}
