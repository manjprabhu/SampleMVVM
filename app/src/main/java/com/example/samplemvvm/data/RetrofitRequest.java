package com.example.samplemvvm.data;

import com.example.samplemvvm.model.Response;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitRequest {
//https://api.stackexchange.com/answers?order=desc&sort=activity&site=stackoverflow&page=7&pageSize=8
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Response> getResponse();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Single<Response> getRxResponse();


    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<Response> getPagedResponse(@Query("page") long page,
                         @Query("pageSize") int pageSize);

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Single<Response> getPagedData(@Query("page") long page,
                                      @Query("pageSize") int pageSize);
}
