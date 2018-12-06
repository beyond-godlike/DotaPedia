package com.unava.dia.dotapedia.network;

import com.unava.dia.dotapedia.network.service.AccInformation;

import okhttp3.logging.HttpLoggingInterceptor;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance;
    private static APIInterface apiService;

    public static APIClient getInstance() {
        if (instance == null) instance = new APIClient();
        return instance;
    }

    private APIClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.opendota.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIInterface.class);

    }

    public Observable<AccInformation> getAccountInfo(String playerId) {
        return apiService.getAccountInformation(playerId);
    }
}
