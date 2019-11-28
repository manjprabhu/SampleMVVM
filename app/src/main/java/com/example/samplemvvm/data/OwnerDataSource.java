package com.example.samplemvvm.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class OwnerDataSource extends PageKeyedDataSource<Integer, Item> {

    private final String TAG = OwnerDataSource.class.getSimpleName();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);

        request.getPagedResponse(1, params.requestedLoadSize).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {
                    Log.v(TAG,"retrofit response model success-->"+response.body().getList().size());
                    callback.onResult(response.body().getList(),null, 5);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG,"retrofit response model failure-->"+t.toString());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);


        request.getPagedResponse(params.key, params.requestedLoadSize).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {

                    Integer key =  response.body().isHasMore() ? params.key + 1 : null;
                    Log.v(TAG,"retrofit response loadafter model success-->"+response.body().getList().size());
                    callback.onResult(response.body().getList(), key);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG,"retrofit response model failure-->"+t.toString());
            }
        });
    }
}
