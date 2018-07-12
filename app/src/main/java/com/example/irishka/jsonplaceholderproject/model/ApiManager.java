package com.example.irishka.jsonplaceholderproject.model;

import com.example.irishka.jsonplaceholderproject.BuildConfig;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static ApiManager apiManager;

    private ITypicodeApi typicodeApi;

    public ITypicodeApi getTypicodeApi() {
        return typicodeApi;
    }

    private ApiManager(){
    }

    public static ApiManager getInstance() {

        if (apiManager == null) {
            apiManager = new ApiManager();
            apiManager.init();
        }

        return apiManager;
    }

    private void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        typicodeApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(ITypicodeApi.class);

    }
}
