package com.example.samplemovieapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by sandeepsaini on 29,May,2019
 */
public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
