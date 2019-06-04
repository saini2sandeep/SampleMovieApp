package com.example.samplemovieapp.view.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 29,May,2019
 */
@Module
public class HomeFragmentModule {

    @Provides
    HomeFragmentMovieListAdapter provideHomeFragmentMovieListAdapter(HomeFragment homeFragment) {
        return new HomeFragmentMovieListAdapter();
    }

    @Provides
    LinearLayoutManager provideGridLayoutManager(HomeFragment homeFragment) {
        return new GridLayoutManager(homeFragment.getContext(), 2, LinearLayoutManager.VERTICAL, false);
    }
}
