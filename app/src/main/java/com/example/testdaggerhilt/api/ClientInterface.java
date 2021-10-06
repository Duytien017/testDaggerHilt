package com.example.testdaggerhilt.api;

import com.example.testdaggerhilt.model.BaseModel;
import com.example.testdaggerhilt.model.Movie;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ClientInterface {
    @Headers("app_token: dCuW7UQMbdvpcBDfzolAOSGFIcAec11a")
    @GET("movie/list/")
    Single<BaseModel> getFilm(@Query("page") int page);
}
