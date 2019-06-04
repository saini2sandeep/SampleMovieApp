package com.example.samplemovieapp.view.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.samplemovieapp.R;
import com.example.samplemovieapp.database.entity.EntityMovie;
import com.example.samplemovieapp.databinding.FragHomeBinding;
import com.example.samplemovieapp.view.MainActivityViewModel;
import com.example.samplemovieapp.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by sandeepsaini on 29,May,2019
 */
public class HomeFragment extends BaseFragment<MainActivityViewModel, FragHomeBinding>
        implements HomeFragmentMovieListAdapter.MovieClickListener {

    @Inject
    MainActivityViewModel mainActivityViewModel;

    @Inject
    HomeFragmentMovieListAdapter movieListAdapter;

    private FragmentCommunicationListener fragmentCommunicationListener;

    private List<EntityMovie> movieList = new ArrayList<>();
    private boolean isCallFromNetwork = false;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public void setFragmentCommunicationListener(FragmentCommunicationListener fragmentCommunicationListener) {
        this.fragmentCommunicationListener = fragmentCommunicationListener;
    }

    @Override
    protected MainActivityViewModel getViewModel() {
        return mainActivityViewModel;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frag_home;
    }

    @Override
    public void initObservers() {

        mainActivityViewModel.getMovieList().observe(this, this::inItRecyclerView);

    }

    @Override
    public void setUp(View view) {

        if (movieList == null || movieList.isEmpty()) {
            isCallFromNetwork = true;
            dataBinding.setShouldShowLoadingBar(true);
            mainActivityViewModel.fetchMovieList();
        } else {
            isCallFromNetwork = false;
        }
    }

    private void inItRecyclerView(List<EntityMovie> movieList) {

        this.movieList.addAll(Objects.requireNonNull(movieList));

        dataBinding.movieListRv.setHasFixedSize(true);
        dataBinding.setShouldShowLoadingBar(false);
        dataBinding.movieListRv.setLayoutManager(
                new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        dataBinding.movieListRv.setAdapter(movieListAdapter);
        movieListAdapter.clearList(!isCallFromNetwork);
        movieListAdapter.addData(movieList);

        movieListAdapter.setMovieClickListener(this);
    }

    @Override
    public void onMovieClick(EntityMovie movie) {
        if (fragmentCommunicationListener != null) {
            fragmentCommunicationListener.onMovieClick(movie);
        }
    }

    @Override
    public void onLikeButtonClick(EntityMovie movie, boolean isFavorite) {

        movie.setFavorite(isFavorite);

        if (movieListAdapter != null)
            movieListAdapter.refreshItem(movie);
    }
}
