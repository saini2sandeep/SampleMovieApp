package com.example.samplemovieapp.di;

import android.app.Application;

import com.example.samplemovieapp.SampleMovieApp;
import com.example.samplemovieapp.di.builder.ActivityBuilderModule;
import com.example.samplemovieapp.di.module.AppModule;
import com.example.samplemovieapp.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by sandeepsaini on 28,May,2019
 * <p>
 * Dagger Component : Bridge between Inject {@link javax.inject.Inject} and Module {@link dagger.Module} annotations
 * Above modules provides Dependencies to the particular activities declared in ActivityBuilderModule {@link ActivityBuilderModule}
 */

@Singleton
@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(SampleMovieApp sampleMovieApp);
}
