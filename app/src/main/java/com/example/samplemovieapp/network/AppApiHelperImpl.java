package com.example.samplemovieapp.network;

import com.example.samplemovieapp.view.home.model.MoviesListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,May,2019
 */
@Singleton
public class AppApiHelperImpl implements AppApiHelper {

    private MovieApiService movieApiService;

    @Inject
    AppApiHelperImpl(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    @Override
    public Single<Response<MoviesListResponse>> fetchMovieList(String type, long page) {
        return movieApiService.fetchMoviesByType(type, page);
    }
}
