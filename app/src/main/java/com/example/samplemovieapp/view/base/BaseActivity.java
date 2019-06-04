package com.example.samplemovieapp.view.base;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import dagger.android.AndroidInjection;

/**
 * Created by sandeepsaini on 28,May,2019
 */
public abstract class BaseActivity<Binding extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {


    /**
     * ViewModel
     */
    protected VM viewModel;


    /**
     * Binding Layout
     */
    public Binding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        performDependencyInjection();
        setUpViewModel();
        setUpToastMessage();

        dataBinding = DataBindingUtil.setContentView(this, getLayoutResource());
        initObservers();
        setUp();
    }

    private void performDependencyInjection() {

        AndroidInjection.inject(this);
    }

    private void setUpViewModel() {

        //        Both way of writing this are fine

        if (viewModel == null)
            this.viewModel = getViewModel();

        //        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }


    private void setUpToastMessage() {
        viewModel.getToastMessageLiveEvent().observe(this, (Observer<String>) this::showToast);
    }

    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void onFragmentAttached() {
        //do nothing
    }

    /**
     * @return iewModel instance
     * @Override for set view model
     */

    protected abstract int getLayoutResource();

    protected abstract VM getViewModel();

    protected abstract void initObservers();

    protected abstract void setUp();

}
