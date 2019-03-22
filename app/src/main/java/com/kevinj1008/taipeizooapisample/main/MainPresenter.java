package com.kevinj1008.taipeizooapisample.main;

import android.util.Log;

import com.kevinj1008.taipeizooapisample.api.ApiConstants;
import com.kevinj1008.taipeizooapisample.api.ApiHelper;
import com.kevinj1008.taipeizooapisample.api.ApiService;
import com.kevinj1008.taipeizooapisample.api.bean.GetZoos;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse;
import com.kevinj1008.taipeizooapisample.model.result.ZooResult;
import com.kevinj1008.taipeizooapisample.util.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Retrofit retrofit = ApiHelper.get(ApiConstants.BASE_URL);

        ApiService service = retrofit.create(ApiService.class);
        service.getZooResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ZooResponse>() {
                    @Override
                    public void onSuccess(ZooResponse zooResponse) {
                        ZooResult zooResult = zooResponse.getZooResult();
                        GetZoos zoos = new GetZoos();
                        zoos.getZoos().addAll(zooResult.getZoos());
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
    public void openPlant(Zoo zoo) {

    }

    @Override
    public void refresh() {

    }

}
