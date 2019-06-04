package com.example.samplemovieapp.view;

import android.arch.lifecycle.ViewModelProvider;

import com.example.samplemovieapp.datamanager.AppDataManager;
import com.example.samplemovieapp.di.ViewModelProviderFactory;
import com.example.samplemovieapp.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sandeepsaini on 28,May,2019
 */
@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel provideMainActivityViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        return new MainActivityViewModel(appDataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(MainActivityViewModel mainActivityViewModel) {
        return new ViewModelProviderFactory<>(mainActivityViewModel);
    }
}
