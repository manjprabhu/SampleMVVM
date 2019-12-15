package com.example.samplemvvm.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;

import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.repository.Repository;

public class FeedDataSourceFactory extends DataSource.Factory<Integer, Item> {

    private final String TAG  = FeedDataSourceFactory.class.getSimpleName();
    private MutableLiveData<OwnerDataSource> data;
    private Repository mRepository;

    public FeedDataSourceFactory(Repository repository) {
        this.data = new MutableLiveData<OwnerDataSource>();
        this.mRepository = repository;
    }

    @Override
    public DataSource<Integer, Item> create() {
        Log.v(TAG,"DataSource factory");
        OwnerDataSource feedDataSource = new OwnerDataSource(mRepository);
        data.postValue(feedDataSource);
        return feedDataSource;
    }
}
