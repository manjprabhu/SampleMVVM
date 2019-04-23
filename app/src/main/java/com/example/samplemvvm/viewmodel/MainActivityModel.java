package com.example.samplemvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.samplemvvm.data.RetrofitClient;
import com.example.samplemvvm.data.RetrofitRequest;
import com.example.samplemvvm.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivityModel extends ViewModel {

    private final String TAG = MainActivityModel.class.getSimpleName();

    public void initRequest() {

        RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);

        request.getResponse().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.v(TAG,"retrofit response model success");

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG,"retrofit response model failure-->"+t.toString());
            }
        });
    }


}
