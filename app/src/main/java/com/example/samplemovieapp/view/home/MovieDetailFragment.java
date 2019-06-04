package com.example.samplemovieapp.view.home;

import android.os.Bundle;
import android.view.View;

import com.example.samplemovieapp.AppConstants;
import com.example.samplemovieapp.R;
import com.example.samplemovieapp.database.entity.EntityMovie;
import com.example.samplemovieapp.databinding.FragMovieDetailBinding;
import com.example.samplemovieapp.view.MainActivityViewModel;
import com.example.samplemovieapp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by sandeepsaini on 30,May,2019
 */
public class MovieDetailFragment extends BaseFragment<MainActivityViewModel, FragMovieDetailBinding> {

    @Inject
    MainActivityViewModel mainActivityViewModel;

    private EntityMovie movie;

    private FragmentCommunicationListener fragmentCommunicationListener;


    public static MovieDetailFragment newInstance(EntityMovie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.BUNDLE_MOVIE, movie);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

    public void setListener(FragmentCommunicationListener fragmentCommunicationListener) {
        this.fragmentCommunicationListener = fragmentCommunicationListener;
    }


    @Override
    protected MainActivityViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_movie_detail;
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void setUp(View view) {

        if (getArguments() != null) {
            movie = getArguments().getParcelable(AppConstants.BUNDLE_MOVIE);

            dataBinding.setMovie(movie);
        }

        dataBinding.backButtonIv.setOnClickListener(v -> {

            if (fragmentCommunicationListener != null)
                fragmentCommunicationListener.onBackButtonClick();
        });

    }
}
