package com.kevinj1008.taipeizooapisample.repositry;

import com.kevinj1008.taipeizooapisample.api.ApiConstants;
import com.kevinj1008.taipeizooapisample.api.ApiHelper;
import com.kevinj1008.taipeizooapisample.api.ApiService;
import com.kevinj1008.taipeizooapisample.model.response.ZooResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ZooRepository {

    public static Single<ZooResponse> getZoo() {
        return ApiHelper.get(ApiConstants.BASE_URL)
                .create(ApiService.class)
                .getZooResponse()
                .subscribeOn(Schedulers.io());
    }
}
