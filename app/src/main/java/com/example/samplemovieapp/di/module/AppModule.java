package com.example.samplemovieapp.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.samplemovieapp.database.AppDataBase;
import com.example.samplemovieapp.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 28,May,2019
 */

@Module
public class AppModule {

    @Provides
    @ApplicationContext
    Context providesContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppDataBase providesAppDataBase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "sampleMovieAppDB").fallbackToDestructiveMigration().build();
    }
}
