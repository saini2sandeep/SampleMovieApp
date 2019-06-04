package com.example.samplemovieapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.samplemovieapp.database.AppDbHelper;
import com.example.samplemovieapp.database.AppDbHelperImpl;
import com.example.samplemovieapp.datamanager.AppDataManager;
import com.example.samplemovieapp.datamanager.AppDataManagerImpl;
import com.example.samplemovieapp.di.ApplicationContext;
import com.example.samplemovieapp.network.ApiKeyInterceptor;
import com.example.samplemovieapp.network.AppApiHelper;
import com.example.samplemovieapp.network.AppApiHelperImpl;
import com.example.samplemovieapp.network.MovieApiService;
import com.example.samplemovieapp.network.NetworkConstants;
import com.example.samplemovieapp.network.NetworkInterceptor;
import com.example.samplemovieapp.utils.rx.AppSchedulerProvider;
import com.example.samplemovieapp.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.samplemovieapp.network.NetworkConstants.BASE_URL;

/**
 * Created by sandeepsaini on 29,May,2019
 */
@Module
public class NetworkModule {


    @Provides
    @Singleton
    Gson provideGsonBuilder() {

        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    NetworkInterceptor provideNetworkInterceptor(Application application) {

        return new NetworkInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context, NetworkInterceptor networkInterceptor) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(networkInterceptor);
        okHttpClient.addInterceptor(new ChuckInterceptor(context));
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.addInterceptor(new ApiKeyInterceptor());
        okHttpClient.connectTimeout(NetworkConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(NetworkConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    MovieApiService provideMovieApiService(OkHttpClient okHttpClient, Gson gson, @ApplicationContext Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(MovieApiService.class);
    }

    @Provides
    @Singleton
    AppApiHelper provideAppApiHelper(AppApiHelperImpl appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    AppDataManager provideAppDatabaseManager(AppDataManagerImpl appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    AppDbHelper provideAppDbHelper(AppDbHelperImpl dbHelper) {
        return dbHelper;
    }
}
