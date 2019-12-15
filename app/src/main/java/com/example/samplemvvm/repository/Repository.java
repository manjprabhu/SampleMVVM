package com.example.samplemvvm.repository;

import android.util.Log;

import com.example.samplemvvm.data.RetrofitClient;
import com.example.samplemvvm.data.RetrofitRequest;
import com.example.samplemvvm.model.Response;

import io.reactivex.Single;

public class Repository {

    private final String TAG  = Repository.class.getSimpleName();
    private RetrofitRequest request;
    private static Repository INSTANCE = null;

    public Repository() {
        request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);
    }

    public static Repository getInstace() {
        if(INSTANCE ==null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    public Single<Response> getData(int page, int pagesize) {
        Log.v(TAG,"repository getData");
        return  request.getPagedData(page,pagesize);
    }
}
