package com.example.samplemvvm.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitClient = null;

    private final static String baseUrl =  "https://api.stackexchange.com";

    public static Retrofit getRetrofitClient() {

        if(retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                             .baseUrl("https://api.stackexchange.com/2.2/")
                             .addConverterFactory(GsonConverterFactory.create())
                             .build();
        }
        return retrofitClient;

    }
}
