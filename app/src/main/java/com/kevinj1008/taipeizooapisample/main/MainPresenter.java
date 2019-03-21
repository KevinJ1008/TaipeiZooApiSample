package com.kevinj1008.taipeizooapisample.main;

import android.util.Log;

import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.api.callback.GetZoosCallback;
import com.kevinj1008.taipeizooapisample.api.task.GetZoosTask;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.util.Constants;

import static androidx.core.util.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        mMainView = checkNotNull(mainView, "mainView cannot be null!");
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        loadZoo();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadZoo() {
        new GetZoosTask(new GetZoosCallback() {
            @Override
            public void onCompleted(GetZoos zoos) {
                showZoo(zoos);
                Log.d(Constants.TAG, "Get Zoo Complete: " + zoos.getZoos().size());
            }

            @Override
            public void onError(String errorMessage) {
                Log.d(Constants.TAG, "GetZoosTask error: " + errorMessage);
            }
        }).execute();
    }

    @Override
    public void showZoo(GetZoos zoos) {
        mMainView.showZoo(zoos);
    }

    @Override
    public void openZooDetail(Zoo Zoo) {

    }

    @Override
    public void openPlant(Zoo zoo) {

    }

    @Override
    public void refresh() {

    }

}
