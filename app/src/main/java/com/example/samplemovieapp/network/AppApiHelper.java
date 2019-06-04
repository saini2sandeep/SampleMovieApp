package com.example.samplemovieapp.network;

import com.example.samplemovieapp.view.home.model.MoviesListResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sandeepsaini on 29,May,2019
 */
public interface AppApiHelper {

    Single<Response<MoviesListResponse>> fetchMovieList(@Path("type") String type, @Query("page") long page);

}
