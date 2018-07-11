package com.example.irishka.jsonplaceholderproject.Model;

import com.example.irishka.jsonplaceholderproject.BuildConfig;
import com.example.irishka.jsonplaceholderproject.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager implements ITypicodeApi {

    private final String TAG = "PostsList";

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static ApiManager ApiManager;

    private static ITypicodeApi typicodeApi;

    private ApiManager(){
    }

    public static ITypicodeApi getInstance() {

        if (ApiManager == null) {
            ApiManager = new ApiManager();
            init();
        }

        return ApiManager;
    }

    private static void init() {
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

    public Observable<List<PostModel>> getPosts() {

        return typicodeApi.getPosts();

        }
}
