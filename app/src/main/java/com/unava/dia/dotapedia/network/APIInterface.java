package com.unava.dia.dotapedia.network;

import com.unava.dia.dotapedia.network.service.AccInformation;

import io.reactivex.Observable;

import retrofit2.http.*;

public interface APIInterface {
    // Get Player Profile
    @GET("players/{accountId}")
    Observable<AccInformation> getAccountInformation(@Path("accountId") String accountId);
}
