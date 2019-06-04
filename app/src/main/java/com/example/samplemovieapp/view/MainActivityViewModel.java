package com.example.samplemovieapp.view;

import android.util.Log;

import com.example.samplemovieapp.database.entity.EntityMovie;
import com.example.samplemovieapp.datamanager.AppDataManager;
import com.example.samplemovieapp.utils.livedata.SingleLiveEvent;
import com.example.samplemovieapp.utils.rx.SchedulerProvider;
import com.example.samplemovieapp.view.base.BaseViewModel;
import com.example.samplemovieapp.view.home.model.MoviesListResponse;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

/**
 * Created by sandeepsaini on 28,May,2019
 */
public class MainActivityViewModel extends BaseViewModel {


    SingleLiveEvent<List<EntityMovie>> moviesListLiveEvent = new SingleLiveEvent<>();

    public MainActivityViewModel(AppDataManager appDataManager, SchedulerProvider schedulerProvider) {
        super(appDataManager, schedulerProvider);
    }

    public void fetchMovieList() {

        Disposable disposable = getAppDataManager().fetchMovieList("popular", 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(moviesListResponseResponse -> {

                    moviesListLiveEvent.setValue(moviesListResponseResponse.body().getMovieList());

                    saveDataToDB(moviesListResponseResponse.body().getMovieList());

                }, throwable -> {

                    showToastMessage(throwable.getMessage());

                    fetchDataFromDb();
                });

        getCompositeDisposable().add(disposable);
    }


    public SingleLiveEvent<List<EntityMovie>> getMovieList() {
        return moviesListLiveEvent;
    }

    private void saveDataToDB(List<EntityMovie> movieList) {
        Disposable disposable = getAppDataManager().insertMovieList(movieList)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(successValue -> Log.d("DataBase : ", "saving success"),
                        throwable -> Log.d("DataBase : ", throwable.getMessage()));

        getCompositeDisposable().add(disposable);
    }

    private void fetchDataFromDb() {

        Disposable disposable = getAppDataManager().getAllMovie()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<EntityMovie>>() {
                    @Override
                    public void accept(List<EntityMovie> movieList) throws Exception {
                        moviesListLiveEvent.setValue(movieList);
                    }
                }, throwable -> {
                    Log.d("DataBase : ", throwable.getMessage());
                });
        getCompositeDisposable().add(disposable);

    }

}
