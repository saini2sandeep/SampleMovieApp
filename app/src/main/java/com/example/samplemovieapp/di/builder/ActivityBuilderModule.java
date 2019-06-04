package com.example.samplemovieapp.di.builder;

import com.example.samplemovieapp.view.MainActivity;
import com.example.samplemovieapp.view.MainActivityModule;
import com.example.samplemovieapp.view.home.HomeFragmentProvider;
import com.example.samplemovieapp.view.home.MovieDetailFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandeepsaini on 28,May,2019
 */


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            HomeFragmentProvider.class,
            MovieDetailFragmentProvider.class
    })
    abstract MainActivity mainActivity();
}
