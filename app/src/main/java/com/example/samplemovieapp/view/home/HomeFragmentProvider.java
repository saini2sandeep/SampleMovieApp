package com.example.samplemovieapp.view.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sandeepsaini on 29,May,2019
 */
@Module
public abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = {
            HomeFragmentModule.class,
    })
    abstract HomeFragment homeFragment();
}
