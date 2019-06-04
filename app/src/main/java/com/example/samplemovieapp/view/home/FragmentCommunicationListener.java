package com.example.samplemovieapp.view.home;

import com.example.samplemovieapp.database.entity.EntityMovie;

/**
 * Created by sandeepsaini on 30,May,2019
 */
public interface FragmentCommunicationListener {

    void onMovieClick(EntityMovie movie);

    void onBackButtonClick();
}
