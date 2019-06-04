package com.example.samplemovieapp.database;

import com.example.samplemovieapp.database.entity.EntityMovie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by sandeepsaini on 30,May,2019
 */
@Singleton
public class AppDbHelperImpl implements AppDbHelper {

    private final AppDataBase appDataBase;

    @Inject
    AppDbHelperImpl(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    @Override
    public Single<List<Long>> insertMovieList(List<EntityMovie> movieList) {
        return Single.fromCallable(() -> appDataBase.getDaoMovie().insertMovieList(movieList));
    }

    @Override
    public Single<List<EntityMovie>> getAllMovie() {
        return appDataBase.getDaoMovie().getAllMovie();
    }
}
