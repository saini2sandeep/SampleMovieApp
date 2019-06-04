package com.example.samplemovieapp.datamanager;

import com.example.samplemovieapp.database.AppDbHelper;
import com.example.samplemovieapp.database.entity.EntityMovie;
import com.example.samplemovieapp.network.AppApiHelper;
import com.example.samplemovieapp.view.home.model.MoviesListResponse;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 29,May,2019
 */
@Singleton
public class AppDataManagerImpl implements AppDataManager {

    private AppApiHelper appApiHelper;
    private AppDbHelper appDbHelper;

    @Inject
    public AppDataManagerImpl(AppApiHelper appApiHelper, AppDbHelper appDbHelper) {
        this.appApiHelper = appApiHelper;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public Single<Response<MoviesListResponse>> fetchMovieList(String type, long page) {
        return appApiHelper.fetchMovieList(type, page);
    }


    @Override
    public Single<List<Long>> insertMovieList(List<EntityMovie> movieList) {
        return appDbHelper.insertMovieList(movieList);
    }


    @Override
    public Single<List<EntityMovie>> getAllMovie() {
        return appDbHelper.getAllMovie();
    }
}
