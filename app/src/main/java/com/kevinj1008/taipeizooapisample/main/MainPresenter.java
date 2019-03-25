package com.kevinj1008.taipeizooapisample.main;

import android.util.Log;

import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse;
import com.kevinj1008.taipeizooapisample.model.result.ZooResult;
import com.kevinj1008.taipeizooapisample.repositry.ZooRepository;
import com.kevinj1008.taipeizooapisample.util.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

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
        ZooRepository.getZoo()
                .doOnSubscribe(disposable -> mMainView.showProgressBar(true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ZooResponse>() {
                    @Override
                    public void onSuccess(ZooResponse zooResponse) {
                        ZooResult zooResult = zooResponse.getZooResult();
                        GetZoos zoos = new GetZoos();
                        zoos.getZoos().addAll(zooResult.getZoos());
                        mMainView.showProgressBar(false);
                        showZoo(zoos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(Constants.TAG, "Load Zoos error: " + e.getMessage());
                    }
                });

    }

    @Override
    public void showZoo(GetZoos zoos) {
        mMainView.showZoo(zoos);
    }

    @Override
    public void openZooDetail(Zoo zoo) {
        mMainView.showZooDetailUi(zoo);
    }

    @Override
    public void refresh() {

    }

}
