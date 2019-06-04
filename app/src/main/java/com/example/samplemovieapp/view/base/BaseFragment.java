package com.example.samplemovieapp.view.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by sandeepsaini on 28,May,2019
 */
public abstract class BaseFragment<VM extends BaseViewModel, Binding extends ViewDataBinding>
        extends Fragment  {


    private BaseActivity baseActivity;

    protected VM viewModel;

    protected Binding dataBinding;

    protected abstract VM getViewModel();

    protected abstract int getLayoutResource();

    public abstract void initObservers();

    public abstract void setUp(View view);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        performDependencyInjection();
        super.onCreate(savedInstanceState);

        setUpViewModel();
        setUpToastMessage();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObservers();
        setUp(view);
    }

    private void setUpViewModel() {
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    private void setUpToastMessage() {
        viewModel.getToastMessageLiveEvent().observe(this, (Observer<String>) toastMessage -> {
            if (baseActivity != null)
                baseActivity.showToast(toastMessage);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }
}
