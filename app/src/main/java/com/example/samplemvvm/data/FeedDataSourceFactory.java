package com.example.samplemvvm.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;

import com.example.samplemvvm.model.Item;

public class FeedDataSourceFactory extends DataSource.Factory<Integer, Item> {

    private final String TAG  = FeedDataSourceFactory.class.getSimpleName();
    private MutableLiveData<OwnerDataSource> data;
    private OwnerDataSource feedDataSource;

    public FeedDataSourceFactory() {
        this.data = new MutableLiveData<OwnerDataSource>();
    }

    @Override
    public DataSource<Integer, Item> create() {
        Log.v(TAG,"DataSource factory");
        feedDataSource = new OwnerDataSource();
        data.postValue(feedDataSource);
        return feedDataSource;
    }
}
