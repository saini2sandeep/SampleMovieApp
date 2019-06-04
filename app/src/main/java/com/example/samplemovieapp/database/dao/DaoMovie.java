package com.example.samplemovieapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.samplemovieapp.database.entity.EntityMovie;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by sandeepsaini on 30,May,2019
 */
@Dao
public interface DaoMovie {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insertMovieList(List<EntityMovie> moviesList);

    @Query("DELETE FROM entityMovie")
    void flushTable();

    @Query("SELECT * FROM entityMovie")
    Single<List<EntityMovie>> getAllMovie();
}

