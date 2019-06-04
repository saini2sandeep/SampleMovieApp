package com.example.samplemovieapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.samplemovieapp.AppConstants;
import com.example.samplemovieapp.database.dao.DaoMovie;
import com.example.samplemovieapp.database.entity.EntityMovie;

/**
 * Created by sandeepsaini on 30,May,2019
 */

@Database(entities = {EntityMovie.class,}, version = AppConstants.APP_DB_VERSION)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DaoMovie getDaoMovie();




}
