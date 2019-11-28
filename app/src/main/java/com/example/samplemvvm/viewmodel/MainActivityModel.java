package com.example.samplemvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.example.samplemvvm.data.OwnerDataSource;
import com.example.samplemvvm.data.FeedDataSourceFactory;
import com.example.samplemvvm.model.Item;

import java.util.concurrent.Executor;

public class MainActivityModel extends ViewModel {

    private final String TAG = MainActivityModel.class.getSimpleName();
    private LiveData<PagedList<Item>> pagedList;

    public MainActivityModel() {
        Log.v(TAG,"MainActivityModel");

        FeedDataSourceFactory factory = new FeedDataSourceFactory();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(5)
                        .setPageSize(8).build();

        pagedList = new LivePagedListBuilder(factory,config).build();
    }

    public LiveData<PagedList<Item>> getData() {
        return pagedList;
    }
}
