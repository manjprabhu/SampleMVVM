package com.example.samplemvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.example.samplemvvm.data.FeedDataSourceFactory;
import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.repository.Repository;

public class MainActivityModel extends ViewModel {

    private final String TAG = MainActivityModel.class.getSimpleName();
    private LiveData<PagedList<Item>> pagedList;
    private Repository repository;

    public MainActivityModel() {
        Log.v(TAG,"MainActivityModel");

        repository = Repository.getInstace();

        FeedDataSourceFactory factory = new FeedDataSourceFactory(repository);
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
