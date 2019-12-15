package com.example.samplemvvm.data;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String TAG = RetrofitClient.class.getSimpleName();

    private static Retrofit retrofitClient = null;

    private final static String baseUrl =  "https://api.stackexchange.com";

    private static OkHttpClient client;

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public static Retrofit getRetrofitClient() {

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        if(client ==null) {
            client =  new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new CustomInterceptor())
                    .build();
        }

        if(retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                             .baseUrl(baseUrl)
                             .addConverterFactory(GsonConverterFactory.create())
                             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                             .client(client)
                             .build();
        }
        return retrofitClient;
    }

    private static class CustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request original = chain.request();

            Response response = chain.proceed(original);

            Log.v(TAG,"Response:"+response.toString());

            return response;
        }
    }
}
