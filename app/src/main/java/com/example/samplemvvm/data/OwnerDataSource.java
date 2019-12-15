package com.example.samplemvvm.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.model.Response;
import com.example.samplemvvm.repository.Repository;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OwnerDataSource extends PageKeyedDataSource<Integer, Item> {

    private final String TAG = OwnerDataSource.class.getSimpleName();
    private Repository mRepository;

    public OwnerDataSource(Repository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        Log.v(TAG,"loadInitial");
        mRepository.getData(1,params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .subscribe(result -> callback.onResult(result.getList(),null,8),
                           throwable -> callback.onResult(null,null,8));

        //working code
        /*RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);

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
        });*/
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        Log.v(TAG,"loadAfter");

        mRepository.getData(params.key,params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Response response) {
                        Integer key =  response.isHasMore()? params.key + 1 : null;
                        callback.onResult(response.getList(), key);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG,"OnError:"+e.toString());

                    }
                });
        /* RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);
        request.getPagedResponse(params.key, params.requestedLoadSize).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {

                    Integer key =  response.body().isHasMore() ? params.key + 1 : null;
                    Log.v(TAG,"retrofit response load after model success-->"+response.body().getList().size());
                    callback.onResult(response.body().getList(), key);
                }
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG,"retrofit response model failure-->"+t.toString());
            }
        });*/
    }
}
