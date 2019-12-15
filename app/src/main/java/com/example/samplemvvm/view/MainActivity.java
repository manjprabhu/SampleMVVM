package com.example.samplemvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.samplemvvm.R;
import com.example.samplemvvm.adapters.ResponseAdapter;
import com.example.samplemvvm.adapters.ResponseRecycleAdapter;
import com.example.samplemvvm.data.RetrofitClient;
import com.example.samplemvvm.data.RetrofitRequest;
import com.example.samplemvvm.model.Item;
import com.example.samplemvvm.model.Response;
import com.example.samplemvvm.viewmodel.MainActivityModel;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private ResponseRecycleAdapter mAdapter;
    private ResponseAdapter responseAdapter;
    private RecyclerView mRecyclerView;
    private MainActivityModel activityModel;
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        disposable = new CompositeDisposable();
        initViews();
//        initRequest();
//        initRxRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();

        activityModel = ViewModelProviders.of(MainActivity.this).get(MainActivityModel.class);

        activityModel.getData().observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                Log.v(TAG,"OnChanged:"+items.size());
                if(items !=null ) {
                    responseAdapter.submitList(items);
                } else {
                    Toast.makeText(MainActivity.this,"Error while loading data",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mAdapter = new ResponseRecycleAdapter(new ArrayList<Item>(0),this);

        responseAdapter = new ResponseAdapter(this);
        mRecyclerView.setAdapter(responseAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private void initRequest() {

        RetrofitRequest request = RetrofitClient.getRetrofitClient().create(RetrofitRequest.class);

        request.getResponse().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()) {
                    Log.v(TAG,"retrofit response model success-->"+response.body().getList().get(1).getOwner().getDisplayName());
                    mAdapter.updateResponse(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v(TAG,"retrofit response model failure-->"+t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
