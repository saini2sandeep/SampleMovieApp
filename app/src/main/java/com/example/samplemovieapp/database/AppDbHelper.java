package com.example.samplemovieapp.database;

import com.example.samplemovieapp.database.entity.EntityMovie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by sandeepsaini on 30,May,2019
 */
public interface AppDbHelper {

    Single<List<Long>> insertMovieList(List<EntityMovie> movieList);

    Single<List<EntityMovie>> getAllMovie();

}
