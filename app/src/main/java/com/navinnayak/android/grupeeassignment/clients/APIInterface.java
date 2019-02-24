package com.navinnayak.android.grupeeassignment.clients;

import com.navinnayak.android.grupeeassignment.models.DogApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("breeds/image/random")
    Call<DogApiResponse> getRandomImage();
}