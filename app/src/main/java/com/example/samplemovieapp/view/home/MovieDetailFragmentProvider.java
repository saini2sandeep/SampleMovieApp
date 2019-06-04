package com.example.samplemovieapp.view.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandeepsaini on 30,May,2019
 */
@Module
public abstract class MovieDetailFragmentProvider {

    @ContributesAndroidInjector(modules = {
            MovieDetailFragmentModule.class})
    abstract MovieDetailFragment movieDetailFragment();
}
