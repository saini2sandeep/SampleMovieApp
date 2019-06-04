package com.example.samplemovieapp.view.base;

import android.arch.lifecycle.ViewModel;

import com.example.samplemovieapp.datamanager.AppDataManager;
import com.example.samplemovieapp.utils.livedata.ToastMessageLiveEvent;
import com.example.samplemovieapp.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sandeepsaini on 28,May,2019
 */
public abstract class BaseViewModel extends ViewModel {

    private final ToastMessageLiveEvent toastMessageLiveEvent = new ToastMessageLiveEvent();

    public AppDataManager appDataManager;
    public SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;


    public BaseViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        this.appDataManager = appDataManager;
        this.schedulerProvider = schedulerProvider;

        this.compositeDisposable = new CompositeDisposable();
    }


    public AppDataManager getAppDataManager() {
        return appDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ToastMessageLiveEvent getToastMessageLiveEvent() {
        return toastMessageLiveEvent;
    }

    public void showToastMessage(String toastMessage) {
        toastMessageLiveEvent.setValue(toastMessage);
    }

    @Override
    protected void onCleared() {
        try {
            compositeDisposable.dispose();
            super.onCleared();
        } catch (Exception e) {
            //do nothing
        }
    }

}
